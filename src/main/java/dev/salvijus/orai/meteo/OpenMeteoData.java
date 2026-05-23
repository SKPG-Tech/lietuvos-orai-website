package dev.salvijus.orai.meteo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public @Value class OpenMeteoData {
    ArrayList<ForecastEntry> forecastEntries = new ArrayList<>();

    @JsonCreator
    OpenMeteoData(@JsonProperty("hourly") Map<String, JsonNode> hourlyData) throws Exception {
        ArrayNode time = hourlyData.get("time").asArray();
        ArrayNode temperature = hourlyData.get("temperature_2m").asArray();
        ArrayNode visibility = hourlyData.get("visibility").asArray();
        ArrayNode weatherCode = hourlyData.get("weather_code").asArray();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        for (int i = 0; i < time.size(); i++) {
            forecastEntries.add(new ForecastEntry(df.parse(time.get(i).asString()), temperature.get(i).asFloat(), visibility.get(i).asInt(), weatherCode.get(i).asInt()));
        }
    }
}
