package dev.salvijus.orai;

import dev.salvijus.orai.geolocation.GeoLocationResolver;
import dev.salvijus.orai.geolocation.GeoLocationService;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableCaching
@EnableScheduling
public class ApplicationConfig implements WebMvcConfigurer {
    private final GeoLocationService geoLocationService;

    public ApplicationConfig(GeoLocationService geoLocationService) {
        this.geoLocationService = geoLocationService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new GeoLocationResolver(geoLocationService));
    }
}