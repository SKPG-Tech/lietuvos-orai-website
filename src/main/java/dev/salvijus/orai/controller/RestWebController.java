package dev.salvijus.orai.controller;

import dev.salvijus.orai.geolocation.GeoLocationService;
import dev.salvijus.orai.geolocation.ResolveGeoLocation;
import dev.salvijus.orai.model.GeoLocation;
import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestWebController {
    private final TemplateEngine templateEngine;
    private final GeoLocationService geoLocationService;

    public RestWebController(TemplateEngine templateEngine, GeoLocationService geoLocationService) {
        this.templateEngine = templateEngine;
        this.geoLocationService = geoLocationService;
    }

    @GetMapping(value = "/search", params = "location")
    public String search(@ResolveGeoLocation GeoLocation geoLocation, @RequestParam String location) {
        Map<String, Object> model = new HashMap<>();
        model.put("searchResults", geoLocationService.searchFor(location).results());
        StringOutput output = new StringOutput();
        templateEngine.render("tag/searchResults.jte",model, output);
        return output.toString();
    }
}
