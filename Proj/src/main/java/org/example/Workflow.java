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
        String region = "Provence-Alpes-Cote d'Azur";
        Predicate<WeatherData> byRegion = weatherData -> region.equals(weatherData.getLocation().getRegion());
        List<WeatherData> filteredByRegion = filterFP.filterWeatherData(weatherDataList, byRegion);
        System.out.println("Filtered by region: " + filteredByRegion + "\n");
        double maxTempCelsius = filteredByRegion.stream()
                .mapToDouble(weatherData -> weatherData.getCurrent().gettemperature())
                .max()
                .orElseThrow(() -> new RuntimeException("No weather data available"));
        System.out.println("Max temperature en " + region + ": " + maxTempCelsius + "°C");
        double maxTempFahrenheit = (maxTempCelsius * 9/5) + 32;
        System.out.println("Max temperature in " + region + ": " + maxTempFahrenheit + "°F");
        double maxTemperature = maxTempFahrenheit;

        CityBikeInfoMapper cityBikeInfoMapper = new CityBikeInfoMapper();
        List<CityBikeInfo> bikeList = cityBikeInfoMapper.getBikeData();// Assuming you have a BikeDataMapper class

        int minBikes = 5;
        double minTemperature = 0;

        List<String> filteredCities = filterFP.filterByRegionBikesAndTemperature(bikeList, weatherDataList, region, minBikes, minTemperature);
        System.out.println("En région " + region + ", il y a " + filteredCities.size() + " villes qui contiennent plus de " + minBikes + " vélos et où il fait plus de " + minTemperature + "°C");
        filteredCities.forEach(System.out::println);
    }
}
