package org.example.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataFetcher {
    private static final String API_KEY = "464fe929f92e4eed8e083029251402";  // Replace with a valid API key
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static CompletableFuture<JsonNode> fetchWeather(String city) {
        String url = "https://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=" + city;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(response -> {
                    try {
                        return mapper.readTree(response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
