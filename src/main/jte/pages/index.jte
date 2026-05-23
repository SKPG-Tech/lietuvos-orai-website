@import dev.salvijus.orai.geolocation.GeoLocation

@param GeoLocation geoLocation
@param String message

@template.layouts.page(
    geoLocation = geoLocation,
    content = @`
    <h1 class="text-blue-300">Hello, world!</h1>
    <p>${message}</p>
`)