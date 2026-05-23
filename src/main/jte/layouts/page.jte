@import gg.jte.Content
@import dev.salvijus.orai.geolocation.GeoLocation

@param GeoLocation geoLocation
@param Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lietuvos Orai - ${geoLocation.locality()}, ${geoLocation.city()}</title>
    <link href="/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/htmx.org@2.0.10/dist/htmx.min.js"
            integrity="sha384-H5SrcfygHmAuTDZphMHqBJLc3FhssKjG7w/CeCpFReSfwBWDTKpkzPP8c+cLsK+V"
            crossorigin="anonymous"></script>
    <script src="/js/geo.js" defer></script>
</head>
<body>
<header>
    <h1>Lietuvos Orai</h1>
    <p>${geoLocation.locality()}, ${geoLocation.city()}</p>
    <button onclick="getGeoLocation()">Use current location</button>
</header>
${content}
</body>
</html>