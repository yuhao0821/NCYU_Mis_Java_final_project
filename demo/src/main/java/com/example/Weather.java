package com.example;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Weather {
    private static final String WeatherAPI_KEY = "bd5e378503939ddaee76f12ad7a97608";  // OpenWeather API key
    private static final OkHttpClient client = new OkHttpClient();

    public static String getWeatherInfo(String city) throws IOException {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + WeatherAPI_KEY + "&units=metric";
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String responseBody = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonObject main = jsonObject.getAsJsonObject("main");
            JsonObject weather = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();
            JsonObject wind = jsonObject.getAsJsonObject("wind");
            JsonObject sys = jsonObject.getAsJsonObject("sys");
            JsonObject clouds = jsonObject.getAsJsonObject("clouds");

            return String.format(
                "溫度: %s°C\n天氣: %s\n濕度: %s%%\n氣壓: %s hPa\n風速: %s m/s\n雲量: %s%%\n日出時間: %s\n日落時間: %s",
                main.get("temp").getAsString(),
                weather.get("description").getAsString(),
                main.get("humidity").getAsString(),
                main.get("pressure").getAsString(),
                wind.get("speed").getAsString(),
                clouds.get("all").getAsString(),
                DateTime.LongToString(sys.get("sunrise").getAsLong(),"HH:mm:ss"),
                DateTime.LongToString(sys.get("sunset").getAsLong(),"HH:mm:ss")
            );
        }
    }

}

