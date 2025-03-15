package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.weather.WeatherData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WeatherDataMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<WeatherData> readWeatherDataFromFile(String filename) {
        try (InputStream inputStream = WeatherDataMapper.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + filename);
            }
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
    }

    public List<WeatherData> getWeatherData() {
        return readWeatherDataFromFile("weather_data.json");
    }
}
