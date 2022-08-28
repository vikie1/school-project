package com.example.accidenttracking.controller.accident;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ViewFlipper;

import com.example.accidenttracking.R;
import com.google.android.material.button.MaterialButton;

public class AddNewAccidentActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_accident);

        viewFlipper = findViewById(R.id.accident_progress);

        setUpButtonClicks();
    }

    private void setUpButtonClicks() {
        MaterialButton stepOneBtn = findViewById(R.id.add_accident_step_1);

        stepOneBtn.setOnClickListener(v -> viewFlipper.setDisplayedChild(1));
    }
}