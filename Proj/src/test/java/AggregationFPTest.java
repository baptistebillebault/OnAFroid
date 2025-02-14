import org.example.AggregationFP;
import org.example.model.bike.CityBikeInfo;
import org.example.model.weather.WeatherData;
import org.example.model.weather.CurrentWeather;
import org.example.model.weather.Location;
import org.example.services.CityBikeInfoMapper;
import org.example.services.WeatherDataMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AggregationFPTest {

    @Test
    public void testGetFilteredCities() {
        AggregationFP aggregationFP = new AggregationFP();
        WeatherDataMapper weatherDataMapper = new WeatherDataMapper();
        CityBikeInfoMapper cityBikeInfoMapper = new CityBikeInfoMapper();

        // Load data
        List<WeatherData> weatherList = weatherDataMapper.getWeatherData();
        List<CityBikeInfo> bikeList = cityBikeInfoMapper.getBikeData();
        // Run the filter logic
        List<String> filteredCities = aggregationFP.getFilteredCities(weatherList, bikeList);

        // Assert expected result
        assertEquals(Arrays.asList("Nice", "Marseille", "Toulouse", "Montpellier","Saint-Denis"), filteredCities);
    }
}
