package com.example.accidenttracking.controller.analytics;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.accidenttracking.R;
import com.example.accidenttracking.constants.APIEndPoints;
import com.example.accidenttracking.dto.APIErrorDto;
import com.example.accidenttracking.dto.TimeStatsDto;
import com.example.accidenttracking.util.APICalls;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TimeChartFragment extends Fragment {
    List<BarEntry> barEntries;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_time_chart, container, false);

        barEntries = new ArrayList<>();
        setUpChats();

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
            Map<Integer, String> apiResponse = APICalls.httpGet(APIEndPoints.GET_TIME, "");
            APIErrorDto apiErrorDto;
            Map<String, List<TimeStatsDto>> timeStatsResponse;

            if (apiResponse.containsKey(200)) {
                Type mapType = new TypeToken<Map<String, List<TimeStatsDto>>>() {}.getType();
                timeStatsResponse = new Gson().fromJson(apiResponse.get(200), mapType);
                if (timeStatsResponse != null) {
                    for (String key : timeStatsResponse.keySet()) {
                        requireActivity().runOnUiThread(() -> addBarEntries(timeStatsResponse.get(key)));
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
        });
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
    }

    private void addBarEntries(List<TimeStatsDto> timeStatsDtos) {
        if (timeStatsDtos == null || timeStatsDtos.isEmpty()) return;

        for (int i = 0; i < timeStatsDtos.size(); i++) {
            // adding new entry to our array list with bar
            // entry and passing x and y axis value to it.
            barEntries.add(new BarEntry(i, timeStatsDtos.get(i).getTotalAccidents()));
        }
    }
}