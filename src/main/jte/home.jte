@import dev.salvijus.orai.model.GeoLocation
@import dev.salvijus.orai.meteo.*
@import dev.salvijus.orai.model.CurrentWeather
@import dev.salvijus.orai.model.Forecast
@import org.jspecify.annotations.NonNull
@import java.time.format.DateTimeFormatter
@import java.util.Locale
@import java.text.*

@param @NonNull GeoLocation geoLocation
@param @NonNull CurrentWeather currentWeather
@param @NonNull Forecast forecast

@template.layout.main(
    geoLocation = geoLocation,
    content = @`
    !{DateTimeFormatter df = DateTimeFormatter.ofPattern("kk:mm, MMMM dd 'd.'").withLocale(Locale.of("lt", "LT"));}
    <h1>Orai dabar - ${currentWeather.time().format(df)}</h1>
    @template.tag.weatherIcon(wmoCode = currentWeather.weatherCode())
    <h2>${currentWeather.temperature()}&degC</h2>

    <h1>Orai šiandien - </h1>
    <ul>
        @for(Forecast.Entry entry : forecast.entries())
        <li>
            <div class="flex">
                <span>${entry.time().toString()} <b>${entry.temperature()}</b></span>
                <span>@template.tag.weatherIcon(wmoCode = entry.weatherCode(), size = 16)</span>
            </div>
        </li>
        @endfor
    </ul>
`)