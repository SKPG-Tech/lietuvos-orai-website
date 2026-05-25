package dev.salvijus.orai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.salvijus.orai.model.GeoLocation;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public sealed interface OpenMeteoResponse permits OpenMeteoResponse.Forecast, OpenMeteoResponse.Search {
    record Forecast() implements OpenMeteoResponse { }

    record Search(List<GeoLocation> results) implements OpenMeteoResponse {
        public Search(@JsonProperty("results") ArrayNode resultsArray) {
            List<GeoLocation> results = new ArrayList<>();
            if (resultsArray != null) {
                for (JsonNode result : resultsArray) {
                    results.add(new GeoLocation(result.get("name").asString(), result.get("admin1").asString(),
                            result.get("latitude").asFloat(), result.get("longitude").asFloat(), false));
                }
            }
            this(results);
        }
    }
}