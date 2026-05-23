package dev.salvijus.orai.geolocation;

import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;

import java.util.Optional;
import java.util.stream.Stream;

public class GeoLocationController {
    private static final RestClient client = RestClient.builder().baseUrl("https://nominatim.openstreetmap.org/").build();

    GeoLocation getLocation(float lat, float lon) {
        final String query = "/reverse?lat=%s&lon=%s&format=json&accept-language=lt".formatted(lat, lon);
        JsonNode response = client.get().uri(query).retrieve().body(JsonNode.class);
        if (response == null) return null;

        JsonNode address = response.path("address");
        if (address.isMissingNode()) return null;

        String city = Stream.of("state_district", "city", "state")
                .map(key -> address.path(key).asString())
                .filter(value -> !value.isEmpty())
                .findFirst().orElseThrow(() -> new IllegalStateException("city is empty"));

        String locality = Stream.of("quarter", "town", "village", "suburb")
                .map(key -> address.path(key).asString())
                .filter(value -> !value.isEmpty())
                .findFirst().orElseThrow(() -> new IllegalStateException("locality is empty"));

        return new GeoLocation(city, locality, lat, lon);
    }
}
