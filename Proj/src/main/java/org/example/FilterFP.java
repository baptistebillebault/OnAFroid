package org.example;

import org.example.model.bike.CityBikeInfo;
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

    public List<String> filterByRegionBikesAndTemperature(List<CityBikeInfo> bikeDataList, List<WeatherData> weatherDataList,
                                                          String targetRegion, int minBikes, double minTemperature) {
        return bikeDataList.stream()
                .filter(bike -> bike.getNumberOfBikes() > minBikes) // Cities with more than minBikes available
                .flatMap(bike ->
                        weatherDataList.stream()
                                .filter(weather -> weather.getLocation().getName().equals(bike.getCity())) // Match city names
                                .filter(weather -> weather.getLocation().getRegion().equals(targetRegion)) // Filter by region
                                .filter(weather -> weather.getCurrent().gettemperature() > minTemperature) // Filter by temperature
                                .map(weather -> weather.getLocation().getName()) // Extract city name
                )
                .distinct()
                .collect(Collectors.toList());
    }
}
