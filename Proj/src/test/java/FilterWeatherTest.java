import org.example.FilterFP;
import org.example.ParallelFP;
import org.example.model.weather.CurrentWeather;
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
        Predicate<WeatherData> byCity = weatherData -> weatherData.getCurrent().gettemperature() > temperatureNeeded;
        List<WeatherData> filteredByCity = filterFP.filterWeatherData(weatherDataList, byCity);
        assertEquals(3, filteredByCity.size());
    }

    @Test
    public void testAddOneDegreeSequential() {
        List<WeatherData> originalWeatherData = List.copyOf(weatherDataList); // To check the before state

        // Apply the sequential method
        List<WeatherData> modifiedWeatherData = ParallelFP.addOneDegreeSequential(weatherDataList);

        // Assert the temperature has been incremented by 1 for each data point
        for (int i = 0; i < originalWeatherData.size(); i++) {
            double originalTemperature = originalWeatherData.get(i).getCurrent().gettemperature();
            double modifiedTemperature = modifiedWeatherData.get(i).getCurrent().gettemperature();
            assertEquals(originalTemperature, modifiedTemperature);
        }
    }

    @Test
    public void testAddOneDegreeParallel() {
        List<WeatherData> originalWeatherData = List.copyOf(weatherDataList); // To check the before state

        // Apply the parallel method
        List<WeatherData> modifiedWeatherData = ParallelFP.addOneDegreeParallel(weatherDataList);

        // Assert the temperature has been incremented by 1 for each data point
        for (int i = 0; i < originalWeatherData.size(); i++) {
            double originalTemperature = originalWeatherData.get(i).getCurrent().gettemperature();
            double modifiedTemperature = modifiedWeatherData.get(i).getCurrent().gettemperature();
            assertEquals(originalTemperature, modifiedTemperature);
        }
    }
}
