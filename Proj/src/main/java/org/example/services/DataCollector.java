package org.example.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataCollector {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String OUTPUT_FILE = "weather_data.json";

    public static void main(String[] args) {
         final List<String> cities = List.of(
                "Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes", "Montpellier", "Strasbourg",
                "Bordeaux", "Lille", "Rennes", "Reims", "Saint-Étienne", "Toulon", "Grenoble",
                "Dijon", "Angers", "Nîmes", "Villeurbanne", "Clermont-Ferrand", "Aix-en-Provence",
                "Brest", "Tours", "Limoges", "Amiens", "Perpignan", "Metz", "Boulogne-Billancourt",
                "Besançon", "Orléans", "Saint-Denis", "Argenteuil", "Rouen", "Mulhouse", "Caen", "Nancy",
                "Montreuil", "Saint-Paul", "Tourcoing", "Dunkerque", "Nanterre", "Créteil", "Avignon",
                "Poitiers", "Versailles", "Colombes", "Vitry-sur-Seine", "Aulnay-sous-Bois"
        );
        int requestCount = 1; // Number of calls per city

        ArrayNode allData = mapper.createArrayNode();

        // Run multiple requests per city in parallel
        List<CompletableFuture<JsonNode>> futures = cities.stream()
                .flatMap(city -> IntStream.range(0, requestCount)
                        .mapToObj(i -> DataFetcher.fetchWeather(city))) // Fetch weather
                .collect(Collectors.toList());

        // Wait for all requests to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // Collect results
        futures.forEach(future -> {
            try {
                JsonNode response = future.get(); // Blocking call
                if (response != null) {
                    allData.add(response);
                }
            } catch (Exception e) {
                System.err.println("Failed to fetch weather data: " + e.getMessage());
            }
        });

        // Write to JSON file
        saveToFile(allData);
    }

    private static void saveToFile(ArrayNode data) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(OUTPUT_FILE), data);
            System.out.println("✅ Weather data saved to " + OUTPUT_FILE);
        } catch (IOException e) {
            System.err.println("❌ Error writing to file: " + e.getMessage());
        }
    }
}
