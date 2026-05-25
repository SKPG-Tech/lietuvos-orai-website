@import dev.salvijus.orai.model.GeoLocation
@import java.util.List

@param List<GeoLocation> searchResults

<ul>
@for(GeoLocation searchResult : searchResults)
    <li>
        <a onclick="setGeoLocation(parseFloat('${searchResult.lat()}'), parseFloat('${searchResult.lon()}'))">
            ${searchResult.city()}, ${searchResult.locality()}
        </a>
    </li>
@endfor
</ul>