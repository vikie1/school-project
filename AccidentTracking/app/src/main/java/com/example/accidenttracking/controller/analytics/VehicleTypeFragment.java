package com.example.accidenttracking.controller.analytics;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.accidenttracking.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class VehicleTypeFragment extends Fragment {
    List<BarEntry> barEntries;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vehicle_type, container, false);

        barEntries = new ArrayList<>();
        addBarEntries();
        setUpChats();
        return view;
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

    private void addBarEntries() {
        barEntries.add(new BarEntry(1f, 4));
        barEntries.add(new BarEntry(2f, 6));
        barEntries.add(new BarEntry(3f, 2));
        barEntries.add(new BarEntry(4f, 3));
    }
}