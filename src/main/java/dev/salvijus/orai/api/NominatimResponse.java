package dev.salvijus.orai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.salvijus.orai.model.GeoLocation;
import tools.jackson.databind.node.ObjectNode;

import java.util.stream.Stream;

public sealed interface NominatimResponse permits NominatimResponse.Reverse {
    record Reverse(@JsonProperty("address") ObjectNode address,
                   @JsonProperty("lat") float lat,
                   @JsonProperty("lon") float lon) implements NominatimResponse {
        public Reverse withCoords(float lat, float lon) {
            return new Reverse(address, lat, lon);
        }

        public GeoLocation location() {
            String city = Stream.of("state_district", "city", "state")
                    .map(key -> address.path(key).asString())
                    .filter(value -> !value.isEmpty())
                    .findFirst().orElseThrow(() -> new IllegalStateException("city is empty"));

            String locality = Stream.of("quarter", "town", "village", "suburb")
                    .map(key -> address.path(key).asString())
                    .filter(value -> !value.isEmpty())
                    .findFirst().orElseThrow(() -> new IllegalStateException("locality is empty"));

            return new GeoLocation(city, locality, lat, lon, false);
        }
    }
}