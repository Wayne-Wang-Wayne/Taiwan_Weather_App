package com.example.taiwanweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.example.taiwanweatherapp.service.ApiService;
import com.example.taiwanweatherapp.service.WeatherApiService;
import com.example.taiwanweatherapp.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.WeatherData;
import model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    WeatherApiService weatherApiService;
    EditText editText;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        textView.setMovementMethod(new ScrollingMovementMethod());


        List<String> stringList = new ArrayList<>();
        stringList.add("123");
        stringList.add("321");
        stringList.add("23");
        stringList.add("w");
        stringList.add("12512");
        stringList.add("1414");

        Map<Integer, List<String>> lengthMap = Stream.of(stringList).collect(Collectors.groupingBy(string -> string.length()));;

        weatherApiService = ApiService.getWeatherApiService();

        Call<WeatherResponse> callWeather = weatherApiService.getMoreInformation();




        callWeather.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                WeatherResponse weatherResponse = response.body();



                List<WeatherData> weatherDataList = Util.weatherResponseToData(weatherResponse);

                Map<String, List<WeatherData>> weatherDataMap = Stream.of(weatherDataList).collect(Collectors.groupingBy(weatherData -> weatherData.locationName));

                Log.d("", "");


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String answer = editText.getText().toString();

                        List<WeatherData> answerWeatherData = weatherDataMap.get(answer);

                        List<WeatherData> answerWeatherData2 = Stream.of(weatherDataList).filter(weatherData ->
                            weatherData.locationName.equals(answer) && weatherData.elementName.equals("PoP")
                        ).toList();

                        Log.d("", "");


//

                    }
                });


            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("", "");

            }
        });
    }
}


