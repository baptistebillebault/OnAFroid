package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.weather.WeatherData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class WeatherDataMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<WeatherData> readWeatherDataFromFile(String filename) {
        try {
            return objectMapper.readValue(new File(filename), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
    }

    public static void main(String[] args) {
        List<WeatherData> weatherList = readWeatherDataFromFile("./src/main/resources/weather_data.json");
        weatherList.forEach(System.out::println);
    }
}
