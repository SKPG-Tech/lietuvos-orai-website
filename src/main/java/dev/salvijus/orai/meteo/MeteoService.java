package dev.salvijus.orai.meteo;

import dev.salvijus.orai.api.OpenMeteoApi;
import dev.salvijus.orai.api.OpenMeteoResponse;
import dev.salvijus.orai.model.CurrentWeather;
import dev.salvijus.orai.model.Forecast;
import dev.salvijus.orai.model.GeoLocation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeteoService {
    private final OpenMeteoApi openMeteoApi;

    public MeteoService(OpenMeteoApi openMeteoApi) {
        this.openMeteoApi = openMeteoApi;
    }

    @Cacheable(value = "forecastCache")
    public Forecast getForecast(GeoLocation geoLocation) {
        final OpenMeteoResponse.Forecast response = openMeteoApi.forecast(geoLocation,
                query -> query.queryParam("hourly", "temperature_2m,weather_code"));
        return new Forecast(List.of());
    }

    @Cacheable(value = "currentWeatherCache")
    public CurrentWeather getCurrentWeather(GeoLocation geoLocation) {
        final OpenMeteoResponse.Forecast response = openMeteoApi.forecast(geoLocation,
                query -> query.queryParam("current", "temperature_2m,weather_code"));
        return new CurrentWeather(LocalDateTime.now(), 1, 1);
    }
}
