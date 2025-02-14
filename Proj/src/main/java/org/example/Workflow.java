package org.example;

import org.example.model.bike.CityBikeInfo;
import org.example.model.weather.WeatherData;
import org.example.services.CityBikeInfoMapper;
import org.example.services.WeatherDataMapper;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Workflow {
    public class WeatherBikeData {
        private WeatherData weatherData;
        private CityBikeInfo bikeData;

        public WeatherBikeData(WeatherData weatherData, CityBikeInfo bikeData) {
            this.weatherData = weatherData;
            this.bikeData = bikeData;
        }
    }
    public static void main(String[] args) {
        List<WeatherData> weatherDataList = new WeatherDataMapper().getWeatherData();
        System.out.println("Weather data: " + weatherDataList);
        FilterFP filterFP = new FilterFP();
        Predicate<WeatherData> byRegion = weatherData -> "Ile-de-France".equals(weatherData.getLocation().getRegion());
        List<WeatherData> filteredByRegion = filterFP.filterWeatherData(weatherDataList, byRegion);
        System.out.println("Filtered by region: " + filteredByRegion);
        double maxTempCelsius = filteredByRegion.stream()
                .mapToDouble(weatherData -> weatherData.getCurrent().gettemperature())
                .max()
                .orElseThrow(() -> new RuntimeException("No weather data available"));
        System.out.println("Max temperature in Ile-de-France: " + maxTempCelsius + "°C");
        double maxTempFahrenheit = (maxTempCelsius * 9/5) + 32;
        System.out.println("Max temperature in Ile-de-France: " + maxTempFahrenheit + "°F");
        double maxTemperature = maxTempFahrenheit;

        CityBikeInfoMapper cityBikeInfoMapper = new CityBikeInfoMapper();
        List<CityBikeInfo> bikeList = cityBikeInfoMapper.getBikeData();// Assuming you have a BikeDataMapper class

        List<WeatherBikeData> aggregatedData = filteredByRegion.stream()
                .flatMap(weatherData -> bikeList.stream()
                        .filter(bikeData -> bikeData.getCity().equals(weatherData.getLocation().getName()))
                        .map(bikeData -> new Workflow().new WeatherBikeData(weatherData, bikeData)))
                .collect(Collectors.toList());
    }
}
