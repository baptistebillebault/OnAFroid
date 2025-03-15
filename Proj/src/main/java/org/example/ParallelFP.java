package org.example;

import org.example.model.bike.CityBikeInfo;
import org.example.model.weather.CurrentWeather;
import org.example.model.weather.WeatherData;
import org.example.services.CityBikeInfoMapper;
import org.example.services.WeatherDataMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ParallelFP {

    public static void main(String[] args) {
        WeatherDataMapper weatherDataMapper = new WeatherDataMapper();
        // Get the list of weather data
        List<WeatherData> weatherList = weatherDataMapper.getWeatherData();
        System.out.println(weatherList.get(0).getCurrent().gettemperature());
        addOneDegreeSequential(weatherList);
        System.out.println(weatherList.get(0).getCurrent().gettemperature());
        addOneDegreeParallel(weatherList);
        System.out.println(weatherList.get(0).getCurrent().gettemperature());
    }

    // Sequential method to add 1 degree to each temperature
        public static List<WeatherData> addOneDegreeSequential(List<WeatherData> weatherList) {
            return weatherList.stream()
                    .map(wd -> {
                                CurrentWeather currentWeather = wd.getCurrent();
                                currentWeather.settemperature(currentWeather.gettemperature() + 1);
                                wd.setCurrent(currentWeather);
                                return wd;
                            }
                    ).collect(Collectors.toList());
        }

        // Parallel method to add 1 degree to each temperature
        public static List<WeatherData> addOneDegreeParallel(List<WeatherData> weatherList) {
            return weatherList.parallelStream() // Use parallelStream instead of stream
                    .map(wd -> {
                                CurrentWeather currentWeather = wd.getCurrent();
                                currentWeather.settemperature(currentWeather.gettemperature() + 1);
                                wd.setCurrent(currentWeather);
                                return wd;
                            }
                    ).collect(Collectors.toList());
        }

}
