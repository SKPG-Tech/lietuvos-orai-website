@import gg.jte.Content
@import dev.salvijus.orai.model.GeoLocation

@param GeoLocation geoLocation
@param Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Lietuvos Orai - ${geoLocation.locality()}, ${geoLocation.city()}</title>
    <link rel="icon" type="image/png" sizes="32x32" href="/favicon.png">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://unpkg.com">
    <link rel="preload" href="/font/JosefinSlab-VF.woff2" as="font" type="font/woff2" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=block" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/lucide-static@latest/font/lucide.css">
    <script src="https://cdn.jsdelivr.net/npm/htmx.org@2.0.10/dist/htmx.min.js"
            integrity="sha384-H5SrcfygHmAuTDZphMHqBJLc3FhssKjG7w/CeCpFReSfwBWDTKpkzPP8c+cLsK+V"
            crossorigin="anonymous"></script>
    <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js"></script>
    <script src="/js/geo.js" defer></script>
</head>
<body class="dark bg-background">
<header class="bg-background/75 backdrop-blur-lg flex self-stretch justify-between items-center h-28 px-48 py-3.5 border-b border-b-text-2 sticky z-10 top-0">
    <div class="flex items-center justify-between w-fit gap-5">
        @template.tag.websiteLogo()
        <h1 class="w-32 h-14 justify-start text-3xl">Lietuvos Orai</h1>
    </div>
    <div class="flex items-center justify-between w-fit gap-5">
        @template.tag.search()
        @template.tag.button(icon = "locate", onClick = "getGeoLocation()")
    </div>
</header>
<main class="px-96">
    ${content}
</main>
</body>
</html>