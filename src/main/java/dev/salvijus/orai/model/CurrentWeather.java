package dev.salvijus.orai.model;

import java.time.LocalDateTime;

public record CurrentWeather(
    LocalDateTime time,
    float temperature,
    int weatherCode,
    boolean isDay
) { }
