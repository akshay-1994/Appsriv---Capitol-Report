package com.appsirv.admin.capitolreport_appsirvtechnologies;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MakeHttpRequest {

// This class is for making HTTP request and getting Json / XML object.

    // Response
    private static Response response;

// Function to get data from API

    public static JSONObject getDataFromAPI() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Configurations.REQUEST_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e("failure in response", e.getLocalizedMessage());
        }
    return null;
}
}
