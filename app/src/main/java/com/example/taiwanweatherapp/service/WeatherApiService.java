package com.example.taiwanweatherapp.service;

import model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("v1/rest/datastore/F-C0032-001")
    Call<WeatherResponse> getMoreInformation();
}