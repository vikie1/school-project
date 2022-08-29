package com.example.accidenttracking;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accidenttracking.constants.APIEndPoints;
import com.example.accidenttracking.controller.common.BottomNav;
import com.example.accidenttracking.dto.APIErrorDto;
import com.example.accidenttracking.dto.AccidentDto;
import com.example.accidenttracking.util.APICalls;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNav.init(getWindow().getDecorView().getRootView(), this);


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

    private void getRecords() {
        new Thread(() -> {
            Map<Integer, String> apiResponse = APICalls.httpGet(APIEndPoints.GET_ACCIDENT, "");
            APIErrorDto apiErrorDto;
            Map<String, List<AccidentDto>> accidentData;
            if (apiResponse.containsKey(200)) {
                Type mapType = new TypeToken<Map<String, List<AccidentDto>>>() {
                }.getType();
                accidentData = new Gson().fromJson(apiResponse.get(200), mapType);
                if (accidentData != null) {
                    for (String key : accidentData.keySet()) {
                        runOnUiThread(() -> setUpQuickStatsCount(accidentData.get(key)));
                    }
                }
            } else {
                for (int errorResponse : apiResponse.keySet()) {
                    apiErrorDto = new Gson().fromJson(apiResponse.get(errorResponse), APIErrorDto.class);
                    APIErrorDto finalApiErrorDto = apiErrorDto;
                    runOnUiThread(() -> setUpLatestRecords(
                            "Status: " + finalApiErrorDto.getStatus(),
                            "Response Code: " + finalApiErrorDto.getStatusCode(),
                            "Reason: " + finalApiErrorDto.getReason()
                    ));
                }
            }
        }).start();
    }

    private void setUpQuickStatsCount(List<AccidentDto> accidentDtos) {
        if (accidentDtos == null || accidentDtos.size() == 0) return;

        TextView totalAccidents = findViewById(R.id.total_accidents_count);
        TextView totalCasualties = findViewById(R.id.total_casualties_count);
        TextView accidentsReportedToday = findViewById(R.id.total_accidents_reported_today_count);
        TextView casualtiesToday = findViewById(R.id.total_casualties_today_count);

        totalAccidents.setText(String.valueOf(accidentDtos.size()));

        int casualties = 0, accidentsToday = 0, casualtiesReportedToday = 0;
        LocalDateTime previousDate = null;

        for (AccidentDto accidentDto: accidentDtos){
            casualties += accidentDto.getAccidentData().getTotalCasualties();
            LocalDateTime accidentDate = LocalDateTime.parse(accidentDto.getAccidentData().getTime());

            if (accidentDate.toLocalDate().equals(LocalDate.now())){
                accidentsToday ++;
                casualtiesReportedToday += accidentDto.getAccidentData().getTotalCasualties();
            }

            if (previousDate == null) {
                setUpLatestRecords(
                        "New Accident Alert",
                        "Area: " + accidentDto.getLocation().getAddress(),
                        "Cause: " + accidentDto.getAccidentData().getCause()
                );
                previousDate = accidentDate;
            }
            else if (accidentDate.isAfter(previousDate)){
                setUpLatestRecords(
                        "New Accident Alert",
                        "Area: " + accidentDto.getLocation().getAddress(),
                        "Cause: " + accidentDto.getAccidentData().getCause()
                );
                previousDate = accidentDate;
            }
        }

        totalCasualties.setText(String.valueOf(casualties));
        accidentsReportedToday.setText(String.valueOf(accidentsToday));
        casualtiesToday.setText(String.valueOf(casualtiesReportedToday));
    }

    public void setUpLatestRecords(String text1, String text2, String text3){
        TextView textView1 = findViewById(R.id.textview1);
        TextView textView2 = findViewById(R.id.textview2);
        TextView textView3 = findViewById(R.id.textview3);

        textView1.setText(text1);
        textView2.setText(text2);
        textView3.setText(text3);
    }
}