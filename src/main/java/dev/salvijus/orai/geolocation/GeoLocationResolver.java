package dev.salvijus.orai.geolocation;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GeoLocationResolver implements HandlerMethodArgumentResolver {
    private static final GeoLocationController geoLocationController = new GeoLocationController();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(GeoLocate.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final Map<String, String> cookies = Arrays.stream(request.getCookies())
                .collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
        String lat = cookies.get("user_lat");
        String lon = cookies.get("user_lon");
        if (lat == null || lon == null) {
            lat = request.getHeader("CF-IPLatitude");
            lon = request.getHeader("CF-IPLongitude");
        }
        if (lat == null || lon == null)
            return new GeoLocation("Vilnius", "Senamiestis", 54.6872f, 25.2797f);
        else
            return geoLocationController.getLocation(Float.parseFloat(lat), Float.parseFloat(lon));
    }
}
