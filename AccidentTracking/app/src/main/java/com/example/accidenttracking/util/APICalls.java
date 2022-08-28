package com.example.accidenttracking.util;

import com.example.accidenttracking.constants.APIEndPoints;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class APICalls {
    public static void httpPost(String jsonData, String endPoint){
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(endPoint).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            OutputStream outputStream = connection.getOutputStream();
            byte[]  data = jsonData.getBytes(StandardCharsets.UTF_8);
            outputStream.write(data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
