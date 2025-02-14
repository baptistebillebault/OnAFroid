package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {

    @JsonProperty("temp_c")
    private double temperatureC;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("wind_kph")
    private double windSpeed;

    @JsonProperty("condition")
    private WeatherCondition condition;

    // Getters and Setters
    public double getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(double temperatureC) {
        this.temperatureC = temperatureC;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public void setCondition(WeatherCondition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "temperatureC=" + temperatureC +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", condition=" + condition +
                '}';
    }
}
