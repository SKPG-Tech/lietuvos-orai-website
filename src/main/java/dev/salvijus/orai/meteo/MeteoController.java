package dev.salvijus.orai.meteo;

import org.springframework.web.client.RestClient;

import java.util.Objects;

public class MeteoController {
    private static final RestClient client = RestClient.builder().baseUrl("https://api.open-meteo.com/v1").build();

    public OpenMeteoData getForecast(float lat, float lon) {
        final String query = "/forecast?latitude=%s&longitude=%s&hourly=temperature_2m,weather_code,visibility".formatted(lat, lon);
        return Objects.requireNonNull(client.get().uri(query).retrieve().body(OpenMeteoData.class));
    }
}
