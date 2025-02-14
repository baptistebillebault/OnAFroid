import org.example.AggregationFP;
import org.example.model.bike.CityBikeInfo;
import org.example.model.weather.WeatherData;
import org.example.model.weather.CurrentWeather;
import org.example.model.weather.Location;
import org.example.services.CityBikeInfoMapper;
import org.example.services.WeatherDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AggregationFPTest {
    public List<CityBikeInfo> bikeList;
    public List<WeatherData> weatherList;
    @BeforeEach
    public void setUp() {
        WeatherDataMapper weatherDataMapper = new WeatherDataMapper();
        CityBikeInfoMapper cityBikeInfoMapper = new CityBikeInfoMapper();
        weatherList = weatherDataMapper.getWeatherData();
        bikeList = cityBikeInfoMapper.getBikeData();
    }

    @Test
    public void testGetFilteredCities() {
        AggregationFP aggregationFP = new AggregationFP();
        // Run the filter logic
        List<String> filteredCities = aggregationFP.getFilteredCities(weatherList, bikeList);

        // Assert expected result
        assertEquals(Arrays.asList("Nice", "Marseille", "Toulouse", "Montpellier","Saint-Denis"), filteredCities);
    }

    @Test
    public void testAddOneDegreeToEachCityWithBikes() {
        AggregationFP aggregationFP = new AggregationFP();
        List<Double> oldTemperatures = new ArrayList<>();
        oldTemperatures.add(weatherList.get(0).getCurrent().gettemperature());
        oldTemperatures.add(weatherList.get(1).getCurrent().gettemperature());
        oldTemperatures.add(weatherList.get(5).getCurrent().gettemperature());
        // Run the method to add 1 degree to cities with more than 10 bikes
        List<WeatherData> updatedWeatherList = aggregationFP.addOneDegreeToEachCityWithBikes(weatherList, bikeList);

        // La température de Nantes n'a pas changé
        assertEquals(oldTemperatures.get(0) + 1, updatedWeatherList.get(0).getCurrent().gettemperature()); // Paris
        assertEquals(oldTemperatures.get(1) + 1, updatedWeatherList.get(1).getCurrent().gettemperature()); // Marseille
        assertEquals(oldTemperatures.get(2),3, updatedWeatherList.get(5).getCurrent().gettemperature()); // Nantes
    }
}
