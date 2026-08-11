package dev.salvijus.orai.model;

public record GeoLocation(
    String city, String locality,
    float lat, float lon,
    boolean unspecified
) {
    public static final GeoLocation DEFAULT = new GeoLocation("Vilnius", "Senamiestis",
                                                          54.6872f, 25.2797f, true);

    public double distanceTo(GeoLocation other) {
        final int EARTH_RADIUS = 6_371_000;
        final double phi1 = Math.toRadians(lat);
        final double phi2 = Math.toRadians(other.lat);
        final double dPhi = Math.toRadians(other.lat - lat);
        final double dLambda = Math.toRadians(other.lon - lon);

        final double a = Math.sin(dPhi / 2) * Math.sin(dPhi / 2) +
                         Math.cos(phi1) * Math.cos(phi2) *
                         Math.sin(dLambda / 2) * Math.sin(dLambda / 2);
        return EARTH_RADIUS * (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
    }
}