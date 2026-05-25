package dev.salvijus.orai.api;

public sealed interface NominatimResponse permits NominatimResponse.Reverse {
    record Reverse() implements NominatimResponse {}
}