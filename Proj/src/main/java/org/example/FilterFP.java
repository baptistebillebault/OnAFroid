package org.example;

import org.example.model.weather.WeatherData;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterFP {
    public List<WeatherData> filterWeatherData(List<WeatherData> weatherDataList, Predicate<WeatherData> predicate) {
        return weatherDataList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
