package dev.salvijus.orai.meteo;

public enum ForecastWindow {
    ONE_DAY(1), THREE_DAYS(3), SEVEN_DAYS(7), FOURTEEN_DAYS(14);

    private final int dayCount;

    ForecastWindow(int dayCount) {
        this.dayCount = dayCount;
    }

    public int dayCount() {
        return dayCount;
    }
}
