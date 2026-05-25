package dev.salvijus.orai.meteo;

import dev.salvijus.orai.model.GeoLocation;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

class QueryBuilder {
    private UriBuilder query = new DefaultUriBuilderFactory().builder();

    public QueryBuilder() {
        query = query.path("/forecast")
                .queryParam("timezone", "auto")
                .queryParam("wind_speed_unit", "ms");
    }

    public QueryBuilder location(GeoLocation geoLocation) {
        query = query.queryParam("latitude", geoLocation.lat())
                .queryParam("longitude", geoLocation.lon());
        return this;
    }

    public QueryBuilder hourly() {
        query = query.queryParam("hourly", "temperature_2m,weather_code");
        return this;
    }

    public QueryBuilder current() {
        query = query.queryParam("current", "temperature_2m,weather_code");
        return this;
    }

    public String build() {
        return query.build().toString();
    }
}