@import dev.salvijus.orai.model.GeoLocation
@import dev.salvijus.orai.meteo.*
@import dev.salvijus.orai.model.CurrentWeather
@import dev.salvijus.orai.model.WeatherForecast
@import org.jspecify.annotations.NonNull
@import java.time.format.DateTimeFormatter
@import java.util.Locale
@import java.text.*

@param @NonNull GeoLocation geoLocation
@param CurrentWeather currentWeather
@param WeatherForecast hourlyForecast
@param WeatherForecast weeklyForecast

@template.layout.main(
    geoLocation = geoLocation,
    content = @`
    !{DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm, MMMM dd 'd.'").withLocale(Locale.of("lt", "LT"));}

    <div class="mt-3.5 inline-flex flex-col justify-start items-start gap-5 w-full">
        <div class="size- pl-1.5 pr-2 py-0.75 bg-background-2 rounded-xl inline-flex justify-start items-center gap-1.25">
            <i class="icon-map-pin text-2xl!"></i>
            <span class="text-[16px] font-semibold">${geoLocation.locality()}, ${geoLocation.city()}</span>
        </div>
        <h1 class="m-0">Artimiausia prognozė</h1>
        <div class="w-full h-36 pl-6 pr-4 py-4 bg-background-2 rounded-3xl flex gap-6">
            <div class="flex gap-3.5 items-center">
                @template.tag.weatherIcon(currentWeather = currentWeather, size = 96)
                <div class="flex flex-col">
<%--                    <h3>${currentWeather.time().format(df)}</h3>--%>
                    <span class="text-6xl font-semibold">${String.format("%.0f", currentWeather.temperature())}<span class="text-text-2 text-6xl">&degC</span></span>
                    @template.tag.weatherCondition(wmoCode = currentWeather.weatherCode())
                </div>
            </div>
        </div>
    </div>

    <h1>Valandų eigoje</h1>
    <ul>
        @for(WeatherForecast.Entry entry : hourlyForecast.entries())
        <li>
            <div class="flex">
                <span>${entry.time().toString()} <b>${entry.temperature()}</b></span>
                <span>@template.tag.weatherIcon(entry = entry, size = 16)</span>
            </div>
        </li>
        @endfor
    </ul>

    <h1>Savaitės prognozė</h1>
    <ul>
        @for(WeatherForecast.Entry entry : weeklyForecast.entries())
            <li>
                <div class="flex">
                    <span>${entry.time().toString()} <b>${entry.temperature()}</b></span>
                    <span>@template.tag.weatherIcon(entry = entry, size = 16)</span>
                </div>
            </li>
        @endfor
    </ul>
`)