<div x-data="{ searchOpen: false, query: '', isSearching: false }"
     class="relative flex flex-col items-end"
     @click.outside="searchOpen = false">
    <div class="transition-all! ease-in-out btn overflow-hidden p-0 flex items-center justify-start h-12"
         :class="searchOpen ? 'w-80! duration-300' : 'w-12 duration-300 delay-150'"
         @click="searchOpen = true; query = ''; $refs.results.innerHTML = ''; $nextTick(() => $refs.searchInput.focus())"
         xmlns:x-transition="http://www.w3.org/1999/xhtml">
        <div class="w-12 h-12 shrink-0 flex items-center justify-center cursor-pointer">
            <i class="icon-search text-2xl!"></i>
        </div>
        <div class="flex-1 min-w-0 w-0 transition-opacity ease-in-out"
             :class="searchOpen ? 'opacity-100 duration-300 delay-75 w-0' : 'opacity-0 duration-100 pointer-events-none'">
            <label for="search" class="hidden">Search</label>
            <input type="text" id="search" name="location" placeholder="Įveskite vietą"
                   class="w-full bg-transparent py-2 focus:outline-none"
                   hx-get="/api/search" hx-trigger="keyup changed delay:500ms" hx-target="#search-results"
                   x-ref="searchInput" x-model="query"
                   @input="isSearching = true; $refs.results.innerHTML = ''"
                   @keydown.escape="searchOpen = false; query = ''">
        </div>
    </div>
    <div x-show="query.length > 0 && searchOpen" x-cloak
         xmlns:x-transition="http://www.w3.org/1999/xhtml"
         x-transition:enter="transition-all ease-out duration-200"
         x-transition:leave="transition-all ease-in duration-150"
         x-transition:enter-start="opacity-0 -translate-y-2" x-transition:leave-start="opacity-100 translate-y-0"
         x-transition:enter-end="opacity-100 translate-y-0" x-transition:leave-end="opacity-0 -translate-y-2"
         class="absolute top-full mt-2 w-80 p-4 bg-background-2 outline outline-text-2 rounded-xl z-50 overflow-hidden">
        <div x-show="isSearching" class="space-y-4">
            <p class="text-sm text-text-2">Ieškoma vietos "<span x-text="query"></span>"...</p>
            <div class="flex items-center space-x-3">
                <div class="flex-1 space-y-2">
                    <div class="h-3 bg-white/10 rounded w-4/5 animate-pulse"></div>
                    <div class="h-2 bg-white/5 rounded w-1/2 animate-pulse"></div>
                </div>
            </div>
            <div class="flex items-center space-x-3">
                <div class="flex-1 space-y-2">
                    <div class="h-3 bg-white/10 rounded w-1/4 animate-pulse"></div>
                    <div class="h-2 bg-white/5 rounded w-2/5 animate-pulse"></div>
                </div>
            </div>
        </div>
        <div id="search-results"
             x-ref="results"
             x-show="!isSearching">
        </div>
    </div>
</div>