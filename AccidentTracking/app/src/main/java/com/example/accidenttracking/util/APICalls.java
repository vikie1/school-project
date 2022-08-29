package com.example.accidenttracking.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APICalls {
    public static void httpPost(String jsonData, String endPoint){
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, jsonData);
        Request request = new Request.Builder()
                .url(endPoint)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Map<Integer, String> httpGet(String endpoint, String arguments){
        Map<Integer, String> apiResponse = new HashMap<>(); // int for the response code and string for the response payload
        HttpURLConnection apiConnection;

        try {
            // get api data
            apiConnection = (HttpURLConnection) new URL(endpoint + (arguments != null && arguments.trim().isEmpty() ? arguments : "")).openConnection();
            InputStream responseBody;
            if (apiConnection.getResponseCode() == 200) responseBody = apiConnection.getInputStream();
            else responseBody = apiConnection.getErrorStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseBody, StandardCharsets.UTF_8)); // make response body a readable json

            // create a string containing api data
            StringBuilder stringBuilder = new StringBuilder();
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(currentLine);
            }

            // package response data
            apiResponse.put(apiConnection.getResponseCode(), stringBuilder.toString());

            // close the connection
            responseBody.close();
            bufferedReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return apiResponse;
    }
}
