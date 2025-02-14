import org.example.FilterFP;
import org.example.model.weather.WeatherData;
import org.example.services.WeatherDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterWeatherTest {

    private List<WeatherData> weatherDataList;

    @BeforeEach
    public void setUp() {
        WeatherDataMapper weatherData = new WeatherDataMapper();
        weatherDataList = weatherData.getWeatherData();
    }

    @Test
    public void testFilterByRegion() {
        FilterFP filterFP = new FilterFP();
        Predicate<WeatherData> byRegion = weatherData -> "Ile-de-France".equals(weatherData.getLocation().getRegion());
        List<WeatherData> filteredByRegion = filterFP.filterWeatherData(weatherDataList, byRegion);
        assertEquals(10, filteredByRegion.size());
    }

    @Test
    public void testFilterByCity() {
        FilterFP filterFP = new FilterFP();
        Predicate<WeatherData> byCity = weatherData -> "Nice".equals(weatherData.getLocation().getName());
        List<WeatherData> filteredByCity = filterFP.filterWeatherData(weatherDataList, byCity);

        assertEquals(1, filteredByCity.size());
        assertEquals("Nice", filteredByCity.get(0).getLocation().getName());
    }
    @Test
    public void testFilterByTemperature() {
        FilterFP filterFP = new FilterFP();
        int temperatureNeeded = 13;
        Predicate<WeatherData> byCity = weatherData -> weatherData.getCurrent().getTemperatureC() > temperatureNeeded;
        List<WeatherData> filteredByCity = filterFP.filterWeatherData(weatherDataList, byCity);
        assertEquals(3, filteredByCity.size());
    }
}