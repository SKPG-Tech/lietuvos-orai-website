package dev.salvijus.orai.model;

import java.time.LocalDateTime;
import java.util.List;

public record WeatherForecast(
    List<Entry> entries
) {
    public record Entry (
        LocalDateTime time,
        float temperature,
        int weatherCode,
        boolean isDay
    ) { }
}
