package dev.salvijus.orai.geolocation;

import dev.salvijus.orai.model.GeoLocation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class GeoLocationResolver implements HandlerMethodArgumentResolver {
    private final GeoLocationService geoLocationService;

    public GeoLocationResolver(GeoLocationService geoLocationService) {
        this.geoLocationService = geoLocationService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ResolveGeoLocation.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Cookie[] cookieArray = request.getCookies();
        final Map<String, String> cookies = Arrays.stream(cookieArray != null ? cookieArray : new Cookie[0])
                .collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
        String lat = cookies.get("user_lat");
        String lon = cookies.get("user_lon");
        if (lat == null || lon == null) {
            lat = request.getHeader("CF-IPLatitude");
            lon = request.getHeader("CF-IPLongitude");
        }
        GeoLocation geoLocation = null;
        if (lat != null && lon != null) {
            try {
                geoLocation = geoLocationService.reverseCoords(Float.parseFloat(lat), Float.parseFloat(lon)).location();
            } catch (IllegalStateException _) { }
        }
        if (geoLocation == null) geoLocation = GeoLocation.DEFAULT;
        return geoLocation;
    }
}
