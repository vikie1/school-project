package com.example.accidenttracking.controller.analytics;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.accidenttracking.R;
import com.example.accidenttracking.constants.APIEndPoints;
import com.example.accidenttracking.dto.APIErrorDto;
import com.example.accidenttracking.dto.VehicleTypeStatsDto;
import com.example.accidenttracking.util.APICalls;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VehicleTypeFragment extends Fragment {
    private List<BarEntry> barEntries;
    private List<VehicleTypeStatsDto> vehicleTypeStatsDtos;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vehicle_type, container, false);

        barEntries = new ArrayList<>();

        Handler handler = new Handler();
        Runnable runnableThread = new Runnable() {
            @Override
            public void run() {
                getRecords();
                handler.postDelayed(this, 5000); //wait 4 sec and run again
            }
        };
        runnableThread.run();
        return view;
    }

    private void getRecords() {
        new Thread(() -> {
            Map<Integer, String> apiResponse = APICalls.httpGet(APIEndPoints.GET_CAR_TYPE, "");
            APIErrorDto apiErrorDto;
            Map<String, List<VehicleTypeStatsDto>> carStatsResponse;

            if (apiResponse.containsKey(200)){
                Type mapType = new TypeToken<Map<String, List<VehicleTypeStatsDto>>>(){}.getType();
                carStatsResponse = new Gson().fromJson(apiResponse.get(200), mapType);

                if (carStatsResponse != null){
                    for (String key : carStatsResponse.keySet()){
                        vehicleTypeStatsDtos = carStatsResponse.get(key);
                        requireActivity().runOnUiThread(this::addBarEntries);
                    }
                }
            } else {
                for (int errorResponse : apiResponse.keySet()) {
                    apiErrorDto = new Gson().fromJson(apiResponse.get(errorResponse), APIErrorDto.class);
                    APIErrorDto finalApiErrorDto = apiErrorDto;
                    requireActivity().runOnUiThread(() -> {
                        CharSequence text = finalApiErrorDto.getStatusCode() + finalApiErrorDto.getReason();
                        Snackbar.make(view, text, BaseTransientBottomBar.LENGTH_LONG).show();
                    });
                }
            }
        }).start();
    }

    private void setUpChats() {
        BarChart barChart = view.findViewById(R.id.bar_chart);
        BarDataSet barDataSet = new BarDataSet(barEntries, "Accidents by time interval chart");
        BarData barData = new BarData(barDataSet);

        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);

        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return vehicleTypeStatsDtos.get((int) value).getCarType();
            }
        });
    }

    private void addBarEntries() {
        if (vehicleTypeStatsDtos == null || vehicleTypeStatsDtos.isEmpty()) return;

        for (int i = 0; i < vehicleTypeStatsDtos.size(); i++){
            // adding new entry to our array list with bar
            // entry and passing x and y axis value to it.
            barEntries.add(new BarEntry(i, vehicleTypeStatsDtos.get(i).getTotalAccidents()));
        }

        setUpChats();
    }
}