package dev.salvijus.orai.meteo;

import dev.salvijus.orai.api.OpenMeteoApi;
import dev.salvijus.orai.model.CurrentWeather;
import dev.salvijus.orai.model.GeoLocation;
import dev.salvijus.orai.model.WeatherForecast;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MeteoService {
    private final OpenMeteoApi openMeteoApi;

    public MeteoService(OpenMeteoApi openMeteoApi) {
        this.openMeteoApi = openMeteoApi;
    }

    @Cacheable(value = "forecastCache")
    public WeatherForecast getForecast(GeoLocation geoLocation, ForecastWindow forecastWindow) {
        return openMeteoApi.forecast(geoLocation, query ->
                    query.queryParam("hourly", "temperature_2m,weather_code,is_day")
                        .queryParam("forecast_days", forecastWindow.dayCount())
                )
                .hourly();
    }

    @Cacheable(value = "currentWeatherCache")
    public CurrentWeather getCurrentWeather(GeoLocation geoLocation) {
        return openMeteoApi.forecast(geoLocation, query ->
                    query.queryParam("current", "temperature_2m,weather_code,is_day")
                )
                .current();
    }
}
