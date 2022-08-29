package com.example.accidenttracking.controller.analytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.accidenttracking.R;
import com.example.accidenttracking.constants.APIEndPoints;
import com.example.accidenttracking.dto.APIErrorDto;
import com.example.accidenttracking.dto.AccidentDto;
import com.example.accidenttracking.util.APICalls;
import com.example.accidenttracking.util.LocationUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DistributionFragment extends Fragment implements LocationUtils.LocationPermissionResult {
    private LatLng latLng;
    private LocationUtils locationUtils;

    private GoogleMap googleMap;
    private Marker currentLocationMarker;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        locationUtils = new LocationUtils(DistributionFragment.this, context, DistributionFragment.this);
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            DistributionFragment.this.googleMap = googleMap;
            latLng = locationUtils.getLatLng();

            if (latLng != null) createMarkerForCurrentLoc();

            Handler handler = new Handler();
            Runnable runnableThread = new Runnable() {
                @Override
                public void run() {
                    getRecords();
                    handler.postDelayed(this, 5000); //wait 4 sec and run again
                }
            };
            runnableThread.run();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_distribution, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void createMarkerForCurrentLoc(){
        if(googleMap != null) {
            if (currentLocationMarker != null) currentLocationMarker.remove();
            currentLocationMarker = googleMap.addMarker(new MarkerOptions().position(latLng).title("Current location"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));
        }
    }

    private void createMarkers(GoogleMap googleMap, double latitude, double longitude, String title){
        LatLng currentLocation = new LatLng(latitude, longitude);
        googleMap.addMarker(
                new MarkerOptions()
                        .position(currentLocation)
                        .title(title)
                        .icon(
                                bitmapDescriptorFromVector(context, R.drawable.ic_baseline_report_problem_24)
                        )
        );
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        if (vectorDrawable == null) return null;
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void getRecords() {
        new Thread(() -> {
            Map<Integer, String> apiResponse = APICalls.httpGet(APIEndPoints.GET_ACCIDENT, "");
            APIErrorDto apiErrorDto;
            Map<String, List<AccidentDto>> accidentData;

            if (apiResponse.containsKey(200)){
                Type mapType = new TypeToken<Map<String, List<AccidentDto>>>() {}.getType();
                accidentData = new Gson().fromJson(apiResponse.get(200), mapType);
                if (accidentData != null) {
                    for (String key : accidentData.keySet()){
                        for (AccidentDto accidentDto: Objects.requireNonNull(accidentData.get(key))){
                            if (accidentDto != null){
                                createMarkers(
                                        googleMap,
                                        accidentDto.getLocation().getLatitude(),
                                        accidentDto.getLocation().getLongitude(),
                                        accidentDto.getAccidentData().getType()
                                );
                            }
                        }
                    }
                }
            } else {
                for (int errorResponse : apiResponse.keySet()) {
                    apiErrorDto = new Gson().fromJson(apiResponse.get(errorResponse), APIErrorDto.class);
                    APIErrorDto finalApiErrorDto = apiErrorDto;
                    Toast.makeText(context, finalApiErrorDto.getStatusCode() + finalApiErrorDto.getReason(), Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

    @Override
    public void onPermissionGranted(LatLng latLng) {
        this.latLng = latLng;

        createMarkerForCurrentLoc();
    }

    @Override
    public void onPermissionDeclined() {
        Toast.makeText(context, "App won't function optimum without location permission", Toast.LENGTH_LONG).show();
    }
}