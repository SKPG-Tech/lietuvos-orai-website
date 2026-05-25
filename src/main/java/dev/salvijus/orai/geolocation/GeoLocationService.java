package dev.salvijus.orai.geolocation;

import dev.salvijus.orai.api.NominatimApi;
import dev.salvijus.orai.api.OpenMeteoApi;
import dev.salvijus.orai.api.OpenMeteoResponse;
import dev.salvijus.orai.model.GeoLocation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationService {
    private final NominatimApi nominatimApi;
    private final OpenMeteoApi openMeteoApi;

    public  GeoLocationService(NominatimApi nominatimApi, OpenMeteoApi openMeteoApi) {
        this.nominatimApi = nominatimApi;
        this.openMeteoApi = openMeteoApi;
    }

    @Cacheable(value = "geoLocationCache")
    public GeoLocation getLocation(float lat, float lon) {
//        final String query = "/reverse?lat=%s&lon=%s&format=json&accept-language=lt".formatted(lat, lon);
//        JsonNode response = client.get().uri(query).retrieve().body(OpenStreetMapNominatimData.class);
//        if (response == null) return null;
//
//        JsonNode address = response.path("address");
//        if (address.isMissingNode()) return null;
//
//        String city = Stream.of("state_district", "city", "state")
//                .map(key -> address.path(key).asString())
//                .filter(value -> !value.isEmpty())
//                .findFirst().orElseThrow(() -> new IllegalStateException("city is empty"));
//
//        String locality = Stream.of("quarter", "town", "village", "suburb")
//                .map(key -> address.path(key).asString())
//                .filter(value -> !value.isEmpty())
//                .findFirst().orElseThrow(() -> new IllegalStateException("locality is empty"));
//
//        return new GeoLocation(city, locality, lat, lon, false);
        return new GeoLocation("a", "b", 0, 0, true);
    }

    @Cacheable(value = "geoLocationSearch")
    public OpenMeteoResponse.Search searchFor(String query) {
        return openMeteoApi.search(query);
    }
}
