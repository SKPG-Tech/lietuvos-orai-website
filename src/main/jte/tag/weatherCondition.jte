@param int wmoCode
@param String style = ""

!{
    String message = switch (wmoCode) {
        case 0 -> "Giedra";
        case 1 -> "Mažai debesuota";
        case 2 -> "Debesuota su pragiedruliais";
        case 3 -> "Debesuota";
        case 45, 48 -> "Rūkas";
        case 51, 53, 55 -> "Nedidelis lietus";
        case 56 -> "Nedidelė šlapdriba";
        case 57 -> "Šlapdriba";
        case 61, 63, 80 -> "Lietus";
        case 66, 67 -> "Lijundra";
        case 71 -> "Nedidelis sniegas";
        case 73 -> "Sniegas";
        case 75 -> "Smarkus sniegas";
        case 77 -> "Kruša";
        case 65, 81, 82 -> "Smarkus lietus";
        case 95 -> "Perkūnija";
        default -> "";
    };
}

<p class="${style} text-xl font-semibold max-w-64">${message}</p>