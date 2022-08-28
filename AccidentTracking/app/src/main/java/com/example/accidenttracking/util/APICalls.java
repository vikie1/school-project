package com.example.accidenttracking.util;

import java.io.IOException;

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
}
