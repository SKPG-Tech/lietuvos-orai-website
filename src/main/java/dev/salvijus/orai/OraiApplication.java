package dev.salvijus.orai;

import dev.salvijus.orai.geolocation.GeoLocate;
import dev.salvijus.orai.geolocation.GeoLocation;
import dev.salvijus.orai.meteo.MeteoController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
public class OraiApplication {
    private static final MeteoController meteoController = new MeteoController();

    static void main(String[] args) {
        SpringApplication.run(OraiApplication.class, args);
    }

    @GetMapping("/")
    public String indexPage(@GeoLocate GeoLocation geoLocation, Model model) {
        model.addAttribute("geoLocation", geoLocation);
        return "pages/index";
    }
}