package org.example.model.bike;

public class CityBikeInfo {
    private String city;
    private boolean hasBike;
    private int numberOfBikes;

    // Default constructor for Jackson
    public CityBikeInfo() {
    }

    // Parameterized constructor
    public CityBikeInfo(String city, boolean hasBike, int numberOfBikes) {
        this.city = city;
        this.hasBike = hasBike;
        this.numberOfBikes = numberOfBikes;
    }

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isHasBike() {
        return hasBike;
    }

    public void setHasBike(boolean hasBike) {
        this.hasBike = hasBike;
    }

    public int getNumberOfBikes() {
        return numberOfBikes;
    }

    public void setNumberOfBikes(int numberOfBikes) {
        this.numberOfBikes = numberOfBikes;
    }

    @Override
    public String toString() {
        return "CityBikeInfo{" +
                "city='" + city + '\'' +
                ", hasBike=" + hasBike +
                ", numberOfBikes=" + numberOfBikes +
                '}';
    }
}
