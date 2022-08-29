package com.example.accidenttracking.controller.accident;

import static com.example.accidenttracking.util.LocationUtils.REQUEST_CHECK_SETTINGS;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.accidenttracking.MainActivity;
import com.example.accidenttracking.R;
import com.example.accidenttracking.constants.APIEndPoints;
import com.example.accidenttracking.constants.SpinnerData;
import com.example.accidenttracking.dto.AccidentDto;
import com.example.accidenttracking.pojo.AccidentData;
import com.example.accidenttracking.pojo.CustomLocation;
import com.example.accidenttracking.util.APICalls;
import com.example.accidenttracking.util.LocationUtils;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class AddNewAccidentActivity extends AppCompatActivity implements LocationUtils.LocationPermissionResult{
    private ViewFlipper viewFlipper;

    private AccidentData accidentData;
    private CustomLocation customLocation;
    private AccidentDto accidentDto;

    private LocalTime localTime;
    private LocalDate localDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_accident);

        viewFlipper = findViewById(R.id.accident_progress);

        // populate spinner
        Spinner causalVehicle = findViewById(R.id.cause_vehicle);
        List<String> spinnerEntries = new ArrayList<>(Arrays.asList(SpinnerData.carTypes));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerEntries);
        causalVehicle.setAdapter(spinnerAdapter);

        accidentData = new AccidentData();

        setUpButtonClicks();
        setUpDateAndTimeInput();
        checkLocationPermissions();
    }

    private void setUpDateAndTimeInput() {
        TextInputEditText dateInputText = findViewById(R.id.accident_date);
        TextInputEditText timeInputText = findViewById(R.id.accident_time);

        // initialise the inputs to current time
        dateInputText.setText(LocalDate.now().toString());
        timeInputText.setText(LocalTime.now().toString());

        localDate = LocalDate.now();
        localTime = LocalTime.now();

        dateInputText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                localDate = LocalDate.of(year, month, dayOfMonth);
                dateInputText.setText(localDate.toString());
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        timeInputText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                localTime = LocalTime.of(hourOfDay, minute);
                timeInputText.setText(localTime.toString());
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
        });
    }

    private void setUpButtonClicks() {
        CheckBox atScene = findViewById(R.id.location_determiner);
        MaterialButton stepOneBtn = findViewById(R.id.add_accident_step_1);
        MaterialButton useCurrentLocation = findViewById(R.id.use_current);
        MaterialButton addLocation = findViewById(R.id.add_location);
        MaterialButton otherDetails = findViewById(R.id.add_accident_step_3);
        MaterialButton finalStepBtn = findViewById(R.id.add_accident_final_step);

        AtomicReference<CustomLocation> accidentLocation = new AtomicReference<>(new CustomLocation());
        stepOneBtn.setOnClickListener(v -> {
            fillStepOneDetails();
            if (atScene.isChecked() && customLocation != null) {
                accidentLocation.set(customLocation);
                viewFlipper.setDisplayedChild(2);
            }
            else viewFlipper.setDisplayedChild(1);
        });
        useCurrentLocation.setOnClickListener(v -> {
            accidentLocation.set(customLocation);
            viewFlipper.setDisplayedChild(2);
        });
        addLocation.setOnClickListener(v -> {
            accidentLocation.set(addLocationManually());
            viewFlipper.setDisplayedChild(2);
        });
        otherDetails.setOnClickListener(v -> {
            fillOtherDetails();
            viewFlipper.setDisplayedChild(3);
        });
        finalStepBtn.setOnClickListener(v -> {
            bundleAccidentReport(accidentLocation.get());
            sendAccidentToAPI();
            Toast.makeText(this, "Accident Report Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddNewAccidentActivity.this, MainActivity.class));
            finish();
        });
    }

    private void bundleAccidentReport(CustomLocation customLocation) {
        TextInputEditText nameInput = findViewById(R.id.reporter_name);
        TextInputEditText emailInput = findViewById(R.id.reporter_email);
        TextInputEditText phoneInput = findViewById(R.id.reporter_contact);

        accidentDto = new AccidentDto(
                Objects.requireNonNull(phoneInput.getText()).toString(),
                Objects.requireNonNull(emailInput.getText()).toString(),
                Objects.requireNonNull(nameInput.getText()).toString(),
                customLocation,
                accidentData
        );
    }

    private void fillStepOneDetails(){
        TextInputEditText accidentType = findViewById(R.id.accident_type);
        TextInputEditText accidentCause = findViewById(R.id.accident_cause);
        Spinner causalVehicle = findViewById(R.id.cause_vehicle);
        TextInputEditText causeVehicleGroup = findViewById(R.id.cause_vehicle_group);
        TextInputEditText vehiclesInvolved = findViewById(R.id.vehicles_involved);
        TextInputEditText passengerCasualties = findViewById(R.id.passenger_casualties);
        TextInputEditText passersbyCasualties = findViewById(R.id.passersby_casualties);
        TextInputEditText otherCasualties = findViewById(R.id.other_casualties);

        accidentData.setCause(Objects.requireNonNull(accidentCause.getText()).toString());
        accidentData.setType(Objects.requireNonNull(accidentType.getText()).toString());
        accidentData.setCausalVehicleType(String.valueOf(causalVehicle.getSelectedItem()));
        accidentData.setCausalVehicleGroup(Objects.requireNonNull(causeVehicleGroup.getText()).toString());
        accidentData.setTotalVehiclesInvolved(Integer.parseInt(Objects.requireNonNull(vehiclesInvolved.getText()).toString()));
        accidentData.setPassengerCasualties(Integer.parseInt(Objects.requireNonNull(passengerCasualties.getText()).toString()));
        accidentData.setPassersByCasualties(Integer.parseInt(Objects.requireNonNull(passersbyCasualties.getText()).toString()));
        accidentData.setOtherCasualties(Integer.parseInt(Objects.requireNonNull(otherCasualties.getText()).toString()));
        accidentData.setTotalCasualties(accidentData.getPassengerCasualties() + accidentData.getPassersByCasualties() + accidentData.getOtherCasualties());
    }

    private CustomLocation addLocationManually(){
        TextInputEditText streetAddress = findViewById(R.id.street_address);
        TextInputEditText locality = findViewById(R.id.locality);
        TextInputEditText city = findViewById(R.id.city);
        TextInputEditText pinCode = findViewById(R.id.pin_code);

        return new CustomLocation(
                AddNewAccidentActivity.this,
                String.valueOf(streetAddress.getText()),
                String.valueOf(locality.getText()),
                String.valueOf(city.getText()),
                String.valueOf(pinCode.getText())
        );
    }

    private void fillOtherDetails(){
        TextInputEditText accidentDescription = findViewById(R.id.accident_desc);
        TextInputEditText casualtyDescription = findViewById(R.id.casualties_desc);

        accidentData.setDescription(Objects.requireNonNull(accidentDescription.getText()).toString());
        accidentData.setCasualtiesDescription(Objects.requireNonNull(casualtyDescription.getText()).toString());
        accidentData.setTime(LocalDateTime.of(localDate, localTime));
    }

    @Override
    public void onBackPressed() {
        if (viewFlipper.getDisplayedChild() > 0) viewFlipper.setDisplayedChild(viewFlipper.getDisplayedChild() - 1);
        else super.onBackPressed();
    }

    private void checkLocationPermissions() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        // location was already enabled
        task.addOnSuccessListener(this, locationSettingsResponse -> new LocationUtils(this));

        // location is disabled
        task.addOnFailureListener(this, e -> {
            if (e instanceof ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.

                ResolvableApiException resolvable = (ResolvableApiException) e;
                try {
                    resolvable.startResolutionForResult(AddNewAccidentActivity.this, REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            switch (resultCode) {
                case RESULT_OK:
                    // All required changes were successfully made
                    new LocationUtils(AddNewAccidentActivity.this);
                    break;
                case RESULT_CANCELED:
                    // The user was asked to change settings, but chose not to
                    Toast.makeText(this, "Your location will not be determined", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void sendAccidentToAPI() {
        new Thread(() -> APICalls.httpPost(accidentDto.toString(), APIEndPoints.SAVE_ACCIDENT)).start();
    }

    @Override
    protected void onStart() {
        super.onStart();

        new LocationUtils(AddNewAccidentActivity.this);
    }

    @Override
    public void onPermissionGranted(LatLng latLng) {}

    @Override
    public void onPermissionDeclined() {}

    @Override
    public void onLocationResult(CustomLocation location) {
        LocationUtils.LocationPermissionResult.super.onLocationResult(location);

        this.customLocation = location;
    }
}