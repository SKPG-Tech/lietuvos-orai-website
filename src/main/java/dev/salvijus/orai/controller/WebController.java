package dev.salvijus.orai.controller;

import dev.salvijus.orai.geolocation.ResolveGeoLocation;
import dev.salvijus.orai.meteo.ForecastWindow;
import dev.salvijus.orai.meteo.MeteoService;
import dev.salvijus.orai.model.CurrentWeather;
import dev.salvijus.orai.model.GeoLocation;
import dev.salvijus.orai.model.WeatherForecast;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    private final MeteoService meteoService;

    public WebController(MeteoService meteoService) {
        this.meteoService = meteoService;
    }

    @GetMapping("/")
    public String homePage(@ResolveGeoLocation GeoLocation geoLocation, Model model) {
        CurrentWeather currentWeather = meteoService.getCurrentWeather(geoLocation);
        WeatherForecast weatherForecast = meteoService.getForecast(geoLocation, ForecastWindow.ONE_DAY);
        model.addAttribute("geoLocation", geoLocation);
        model.addAttribute("currentWeather", currentWeather);
        model.addAttribute("weatherForecast", weatherForecast);

        return "home";
    }
}
