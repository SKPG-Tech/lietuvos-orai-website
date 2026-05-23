package dev.salvijus.orai.meteo;

import java.util.Date;

public record ForecastEntry(
    Date time,
    float temperature,
    int visibility,
    int weatherCode
) {
    @Override
    public String toString() {
        return Float.toString(temperature);
    }
}
