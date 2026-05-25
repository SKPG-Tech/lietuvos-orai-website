package dev.salvijus.orai.meteo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MeteoCacheCleaner {
    private static final Logger log = LoggerFactory.getLogger(MeteoCacheCleaner.class);

    @Scheduled(cron = "0 0/15 * * * *")
    @CacheEvict(value = "currentWeatherCache", allEntries = true)
    public void invalidateCurrentWeatherCache() {
        log.info("invalidating current weather cache");
    }

    @Scheduled(cron = "0 0 * * * *")
    @CacheEvict(value = "forecastCache", allEntries = true)
    public void invalidateForecastCache() {
        log.info("invalidating forecast cache");
    }
}