package dev.salvijus.orai.api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NominatimApi {
    private static final String API_URL = "https://nominatim.openstreetmap.org";

    private final RestClient client;

    public NominatimApi(RestClient.Builder clientBuilder) {
        client = clientBuilder.baseUrl(API_URL).build();
    }

    public NominatimResponse.Reverse reverse(float lat, float lon) {
        final RestClient.ResponseSpec response = client.get()
                .uri(query -> query.path("/reverse")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .queryParam("format", "json")
                        .queryParam("accept-language", "lt")
                        .build()
                )
                .retrieve();
        return response.body(NominatimResponse.Reverse.class);
    }
}
