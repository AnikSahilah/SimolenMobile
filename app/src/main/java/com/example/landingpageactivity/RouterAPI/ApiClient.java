package com.example.landingpageactivity.RouterAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://simolen.kidzeroll.com/api/v1/";
    public static final String LOCAL = "https://simolen.kidzeroll.com/storage/";

    private static Retrofit retrofit;
    private static ApiService apiService;

    public static ApiService getClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }
}
