package dev.salvijus.orai.model;

public record GeoLocation(
    String city, String locality,
    float lat, float lon,
    boolean unspecified
) { }