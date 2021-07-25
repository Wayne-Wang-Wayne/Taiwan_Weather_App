package com.example.taiwanweatherapp.service;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {


    private static final String weatherBaseUrl = "https://opendata.cwb.gov.tw/api/";
    private static final String weatherToken = "CWB-FAB67117-0A14-4BE6-87D8-3B8003A37085";

    private static WeatherApiService weatherApiService;

    public static WeatherApiService getWeatherApiService() {

        if (weatherApiService == null) {
            OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request request = chain.request();
                    Request newRequest;

                    newRequest = request.newBuilder().addHeader("Authorization", weatherToken).build();

                    return chain.proceed(newRequest);

                }
            }).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(weatherBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client).build();

            weatherApiService = retrofit.create(WeatherApiService.class);

        }
        return weatherApiService;


    }


}
