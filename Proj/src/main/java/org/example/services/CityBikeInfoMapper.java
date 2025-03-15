package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.bike.CityBikeInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CityBikeInfoMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Method to read bike info from a JSON file in resources
    public static List<CityBikeInfo> readBikeInfoFromFile(String filename) {
        try (InputStream inputStream = CityBikeInfoMapper.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + filename);
            }
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading bike information JSON file", e);
        }
    }

    public List<CityBikeInfo> getBikeData() {
        return readBikeInfoFromFile("bikeInformation.json");
    }
}
