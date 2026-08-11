@import dev.salvijus.orai.model.CurrentWeather
@import dev.salvijus.orai.model.WeatherForecast

@param CurrentWeather currentWeather = null
@param WeatherForecast.Entry entry = null
@param int size = 64

!{
    int wmoCode = currentWeather != null ? currentWeather.weatherCode() : entry.weatherCode();
    boolean isDay = currentWeather != null ? currentWeather.isDay() : entry.isDay();
    String iconName = switch (wmoCode) {
        case 0 -> isDay ? "clear-day" : "clear-night";
        case 1 -> isDay ? "partly-cloudy-day" : "partly-cloudy-night";
        case 2 -> isDay ? "cloudy-with-sunny-intervals-day" : "cloudy-with-sunny-intervals-night";
        case 3 -> "cloudy";
//        case 45, 48 -> "Rūkas";
        case 51, 53, 55 -> "light-rain";
//        case 56 -> "Nedidelė šlapdriba";
//        case 57 -> "Šlapdriba";
        case 61, 63, 80 -> "rain";
//        case 66, 67 -> "Lijundra";
        case 71 -> "light-snow";
        case 73 -> "snow";
        case 75 -> "heavy-snow";
//        case 77 -> "Kruša";
        case 65, 81, 82 -> "heavy-rain";
        case 95 -> "thunder";
        default -> "na";
    };
}

<img src="/img/weather/${iconName}.webp" alt="Weather condition icon" style="height: ${size}px"/>