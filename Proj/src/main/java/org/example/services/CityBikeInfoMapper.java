package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.bike.CityBikeInfo;
import org.example.model.weather.WeatherData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CityBikeInfoMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Method to read bike info from a JSON file
    public static List<CityBikeInfo> readBikeInfoFromFile(String filename) {
        try {
            // Using Jackson to parse the JSON file and map it to a List of CityBikeInfo objects
            return objectMapper.readValue(new File(filename), new TypeReference<List<CityBikeInfo>>() {});
        } catch (IOException e) {
            // Handle file reading errors
            throw new RuntimeException("Error reading bike information JSON file", e);
        }
    }
    public List<CityBikeInfo> getBikeData(){
        return readBikeInfoFromFile("./src/main/resources/bikeInformation.json");
    }

}
