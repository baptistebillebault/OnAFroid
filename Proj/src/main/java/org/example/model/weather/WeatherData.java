package org.example.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    @JsonProperty("location")
    private Location location;

    @JsonProperty("current")
    private CurrentWeather current;

    // Getters and Setters
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "location=" + location +
                ", current=" + current +
                '}';
    }
}
