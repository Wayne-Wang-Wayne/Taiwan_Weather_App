package com.example.taiwanweatherapp.util;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import model.WeatherData;
import model.WeatherResponse;

public class Util {

    public static List<WeatherData> weatherResponseToData(WeatherResponse weatherResponse){

        List<WeatherData> weatherDataList = new ArrayList<>();

        if (weatherResponse == null)
            return weatherDataList;

        Stream.of(weatherResponse.records.location).forEach(location -> {
            Stream.of(location.weatherElement).forEach(weatherElement -> {
                Stream.of(weatherElement.time).forEach(weatherTime -> {

                    WeatherData weatherData = new WeatherData(
                            location.locationName,
                            weatherElement.elementName,
                            weatherTime.startTime,
                            weatherTime.endTime,
                            weatherTime.parameter.parameterName,
                            weatherTime.parameter.parameterValue,
                            weatherTime.parameter.parameterUnit
                    );

                    weatherDataList.add(weatherData);
                });
            });
        });

        return weatherDataList;
    }
}
