import com.fasterxml.jackson.databind.JsonNode;
import org.example.services.DataFetcher;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.*;

public class DataFetcherTest {

    @Test
    public void testFetchWeatherForAntibes() {
        // Fetch weather data for Antibes
        CompletableFuture<JsonNode> future = DataFetcher.fetchWeather("Antibes");

        // Wait for the future to complete
        JsonNode response = future.join();

        // Assertions
        assertNotNull(response, "API response should not be null");
        assertTrue(response.has("location"), "Response should contain 'location' field");
        assertEquals("Antibes", response.get("location").get("name").asText(), "City name should be 'Antibes'");
        assertTrue(response.has("current"), "Response should contain 'current' field");
        assertTrue(response.get("current").has("temp_c"), "Response should contain 'temp_c' field");

        // Print response for debugging
        System.out.println("Weather in Antibes: " + response);
    }
}
