@param int wmoCode
@param int size = 64

!{
    String iconName = switch (wmoCode) {
        case 0, 1 -> "clear";
        case 2 -> "partly-cloudy";
        case 3 -> "cloudy";
        default -> "na";
    };
}

<img src="/img/weather/${iconName}.svg" alt="Weather condition icon" height="${size}"/>