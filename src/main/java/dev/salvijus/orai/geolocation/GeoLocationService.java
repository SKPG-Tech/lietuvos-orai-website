package dev.salvijus.orai.geolocation;

import dev.salvijus.orai.api.NominatimApi;
import dev.salvijus.orai.api.NominatimResponse;
import dev.salvijus.orai.api.OpenMeteoApi;
import dev.salvijus.orai.api.OpenMeteoResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationService {
    private final NominatimApi nominatimApi;
    private final OpenMeteoApi openMeteoApi;

    public GeoLocationService(NominatimApi nominatimApi, OpenMeteoApi openMeteoApi) {
        this.nominatimApi = nominatimApi;
        this.openMeteoApi = openMeteoApi;
    }

    @Cacheable(value = "reverseCache")
    public NominatimResponse.Reverse reverseCoords(float lat, float lon) {
        return nominatimApi.reverse(lat, lon).withCoords(lat, lon);
    }

    @Cacheable(value = "searchCache")
    public OpenMeteoResponse.Search searchFor(String query) {
        return openMeteoApi.search(query);
    }
}
