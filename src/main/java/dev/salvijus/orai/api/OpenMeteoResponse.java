package dev.salvijus.orai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.salvijus.orai.model.CurrentWeather;
import dev.salvijus.orai.model.GeoLocation;
import dev.salvijus.orai.model.WeatherForecast;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public sealed interface OpenMeteoResponse permits OpenMeteoResponse.Forecast, OpenMeteoResponse.Search {
    record Forecast(@JsonProperty("daily") Map<String, JsonNode> dailyData,
                    @JsonProperty("hourly") Map<String, JsonNode> hourlyData,
                    @JsonProperty("current") Map<String, JsonNode> currentData) implements OpenMeteoResponse {
        public CurrentWeather current() {
            if (currentData == null) return null;

            LocalDateTime time = LocalDateTime.parse(currentData.get("time").asString());
            float temp = (float) currentData.get("temperature_2m").asDouble();
            int code = currentData.get("weather_code").asInt();
            boolean isDay = currentData.get("is_day").asBoolean();

            return new CurrentWeather(time, temp, code, isDay);
        }

        public WeatherForecast hourly() {
            if (hourlyData == null) return new WeatherForecast(List.of());

            ArrayNode time = hourlyData.get("time").asArray();
            ArrayNode temperature = hourlyData.get("temperature_2m").asArray();
            ArrayNode weatherCode = hourlyData.get("weather_code").asArray();
            ArrayNode isDay = hourlyData.get("is_day").asArray();
            if (time == null || temperature == null || weatherCode == null || isDay == null)
                return new WeatherForecast(List.of());

            List<WeatherForecast.Entry> entries = IntStream.range(0, time.size())
                    .mapToObj(i -> new WeatherForecast.Entry(
                            LocalDateTime.parse(time.get(i).asString()),
                            temperature.get(i).asFloat(),
                            weatherCode.get(i).asInt(),
                            isDay.get(i).asBoolean()
                    ))
                    .toList();
            return new WeatherForecast(entries);
        }

        public WeatherForecast daily() {
            if (dailyData == null) return new WeatherForecast(List.of());

            ArrayNode time = dailyData.get("time").asArray();
            ArrayNode temperature = dailyData.get("temperature_2m").asArray();
            ArrayNode weatherCode = dailyData.get("weather_code").asArray();
            ArrayNode isDay = dailyData.get("is_day").asArray();
            if (time == null || temperature == null || weatherCode == null || isDay == null)
                return new WeatherForecast(List.of());

            List<WeatherForecast.Entry> entries = IntStream.range(0, time.size())
                    .mapToObj(i -> new WeatherForecast.Entry(
                            LocalDateTime.parse(time.get(i).asString()),
                            temperature.get(i).asFloat(),
                            weatherCode.get(i).asInt(),
                            isDay.get(i).asBoolean()
                    ))
                    .toList();
            return new WeatherForecast(entries);
        }
    }

    record Search(@JsonProperty("results") ArrayNode resultData) implements OpenMeteoResponse {
        public List<GeoLocation> results() {
            return resultData != null
                    ? resultData.valueStream()
                        .map(result -> new GeoLocation(result.get("name").asString(), result.get("admin1").asString(),
                                result.get("latitude").asFloat(), result.get("longitude").asFloat(), false)
                        ).toList()
                    : List.of();
        }
    }
}