package org.example;

import org.example.model.bike.CityBikeInfo;
import org.example.model.weather.WeatherData;
import org.example.services.CityBikeInfoMapper;
import org.example.services.WeatherDataMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AggregationFP {

    // Refactored to be a reusable method that takes input parameters
    public List<String> getFilteredCities(List<WeatherData> weatherList, List<CityBikeInfo> bikeList) {
        return bikeList.stream()
                .filter(bikeInfo -> bikeInfo.getNumberOfBikes() > 10)
                .flatMap(bikeInfo ->
                        weatherList.stream()
                                .filter(weather -> weather.getLocation().getName().equals(bikeInfo.getCity()))
                                .filter(weather -> weather.getCurrent().getTemperatureC() > 10)
                )
                .map(weather -> weather.getLocation().getName())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        WeatherDataMapper weatherDataMapper = new WeatherDataMapper();
        CityBikeInfoMapper cityBikeInfoMapper = new CityBikeInfoMapper();

        // Load data
        List<WeatherData> weatherList = weatherDataMapper.getWeatherData();
        List<CityBikeInfo> bikeList = cityBikeInfoMapper.getBikeData();

        AggregationFP aggregationFP = new AggregationFP();
        List<String> filteredCities = aggregationFP.getFilteredCities(weatherList, bikeList);

        filteredCities.forEach(System.out::println);
    }
}
