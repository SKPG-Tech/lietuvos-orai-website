function setCookie(name, value) {
    const date = new Date();
    date.setTime(date.getTime() + (30 * 24 * 60 * 60 * 1000));
    document.cookie = `${encodeURIComponent(name)}=${encodeURIComponent(value)};expires=${date.toUTCString()};path=/;SameSite=Lax;`;
}

function getGeoLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
            setCookie("user_lat", position.coords.latitude.toFixed(7));
            setCookie("user_lon", position.coords.longitude.toFixed(7));
            location.reload();
        }, (error) => {});
    }
}