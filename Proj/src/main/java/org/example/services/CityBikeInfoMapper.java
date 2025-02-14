package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.bike.CityBikeInfo;

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

    // Main method to test the JSON reading and object mapping
    public static void main(String[] args) {
        // Path to your JSON file
        String filename = "./src/main/resources/bikeInformation.json";

        // Read the bike information from the file
        List<CityBikeInfo> bikeInfoList = readBikeInfoFromFile(filename);

        // Print out each CityBikeInfo object in the list
        bikeInfoList.forEach(System.out::println);
    }
}
