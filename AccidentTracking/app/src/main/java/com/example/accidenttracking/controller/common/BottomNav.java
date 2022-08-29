package com.example.accidenttracking.controller.common;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.accidenttracking.MainActivity;
import com.example.accidenttracking.R;
import com.example.accidenttracking.controller.accident.AddNewAccidentActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNav {
    private BottomNav() {}
    public static void init(View view, Context context) {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(
                item -> {
                    Intent intent;

                    if (item.getItemId() == R.id.home){
                        if (context instanceof MainActivity) return false;
                        intent = new Intent(context, MainActivity.class);
                    } else if(item.getItemId() == R.id.analytics){
                        if (context instanceof MainActivity) return false;
                        intent = new Intent(context, MainActivity.class);
                        Toast.makeText(context, "Okay still progress", Toast.LENGTH_SHORT).show();
                    } else if (item.getItemId() == R.id.report){
                        intent = new Intent(context, AddNewAccidentActivity.class);
                    } else {
                        Toast.makeText(context, "Where did this choice come from!", Toast.LENGTH_SHORT).show();
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                    }

                    context.startActivity(intent);
                    return true;
                });
    }
}
