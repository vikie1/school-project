package com.example.accidenttracking.controller.accident;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.ViewFlipper;

import com.example.accidenttracking.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class AddNewAccidentActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_accident);

        viewFlipper = findViewById(R.id.accident_progress);

        setUpButtonClicks();
        setUpDateAndTimeInput();
    }

    private void setUpDateAndTimeInput() {
        TextInputEditText dateInputText = findViewById(R.id.accident_date);
        TextInputEditText timeInputText = findViewById(R.id.accident_time);

        dateInputText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
                dateInputText.setText(localDate.toString());
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        timeInputText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                LocalTime localTime = LocalTime.of(hourOfDay, minute);
                timeInputText.setText(localTime.toString());
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
        });
    }

    private void setUpButtonClicks() {
        CheckBox atScene = findViewById(R.id.location_determiner);
        MaterialButton stepOneBtn = findViewById(R.id.add_accident_step_1);
        MaterialButton useCurrentLocation = findViewById(R.id.use_current);
        MaterialButton addLocation = findViewById(R.id.add_location);

        stepOneBtn.setOnClickListener(v -> {
            if (atScene.isChecked()) viewFlipper.setDisplayedChild(2);
            else viewFlipper.setDisplayedChild(1);
        });
        useCurrentLocation.setOnClickListener(v -> viewFlipper.setDisplayedChild(2));
        addLocation.setOnClickListener(v -> viewFlipper.setDisplayedChild(2));
    }

    @Override
    public void onBackPressed() {
        if (viewFlipper.getDisplayedChild() > 0) viewFlipper.setDisplayedChild(viewFlipper.getDisplayedChild() - 1);
        else super.onBackPressed();
    }
}