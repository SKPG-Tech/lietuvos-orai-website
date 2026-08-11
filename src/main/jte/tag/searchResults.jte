@import dev.salvijus.orai.model.GeoLocation
@import java.util.List

@param GeoLocation geoLocation
@param List<GeoLocation> searchResults

@if(searchResults.isEmpty())
<div x-init="isSearching = false">
    <span class="text-sm text-text-2">Nieko nerasta užklausai</span>
</div>
@else
<ul x-init="isSearching = false" class="flex flex-col m-0 p-0! list-none">
@for(int i = 0; i < searchResults.size(); i++)
    !{
        GeoLocation searchResult = searchResults.get(i);
        final double distance = geoLocation.distanceTo(searchResult);
        final String distanceString;
        if (distance > 1000) distanceString = String.format("%.2f km", distance / 1000);
        else distanceString = String.format("%.0f m", distance);
    }
    <li x-data="{ visible: false }"
        x-init="$nextTick(() => setTimeout(() => visible = true, (${i} + 1) * 150))"
        onclick="setGeoLocation(parseFloat('${searchResult.lat()}'), parseFloat('${searchResult.lon()}'))"
        :class="visible ? 'opacity-100 translate-y-0' : 'opacity-0 translate-y-2 pointer-events-none'"
        class="flex items-center justify-between p-2 rounded-lg hover:bg-white/10
                cursor-pointer transition-all duration-200 ease-out group">

        <div class="flex flex-col overflow-hidden pr-4">
            <span class="text-text-1 font-medium wrap-normal">${searchResult.city()}</span>
            <span class="text-text-1/90 text-sm truncate">${searchResult.locality()}</span>
        </div>

        <div class="flex items-center space-x-1 shrink-0">
            <i class="icon-navigation text-xs! text-text-2/90!"></i>
            <span class="text-xs text-text-2/90 whitespace-nowrap">${distanceString}</span>
        </div>
    </li>
@endfor
</ul>
@endif