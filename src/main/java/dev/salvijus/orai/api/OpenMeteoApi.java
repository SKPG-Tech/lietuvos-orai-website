package dev.salvijus.orai.api;

import dev.salvijus.orai.model.GeoLocation;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;

import java.util.function.UnaryOperator;

@Component
public class OpenMeteoApi {
    private static final String API_URL = "https://api.open-meteo.com/v1";
    private static final String GEOCODING_URL = "https://geocoding-api.open-meteo.com/v1";

    private final RestClient forecastClient;
    private final RestClient searchClient;

    public OpenMeteoApi(RestClient.Builder clientBuilder) {
        forecastClient = clientBuilder.baseUrl(API_URL).build();
        searchClient = clientBuilder.baseUrl(GEOCODING_URL).build();
    }

    public OpenMeteoResponse.Forecast forecast(GeoLocation geoLocation, UnaryOperator<UriBuilder> queryBuilder) {
        final RestClient.ResponseSpec response = forecastClient.get()
                .uri(query -> queryBuilder.apply(
                        query.path("/forecast")
                                .queryParam("latitude", geoLocation.lat())
                                .queryParam("longitude", geoLocation.lon())
                                .queryParam("timezone", "auto")
                        ).build()
                )
                .retrieve();
        return response.body(OpenMeteoResponse.Forecast.class);
    }

    public OpenMeteoResponse.Search search(String name) {
        final RestClient.ResponseSpec response = searchClient.get()
                .uri(query -> query.path("/search")
                        .queryParam("name", name)
                        .queryParam("countryCode", "LT")
                        .queryParam("language", "lt")
                        .build()
                )
                .retrieve();
        return response.body(OpenMeteoResponse.Search.class);
    }
}
