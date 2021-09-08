package amorallife.config;

import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

public class CorsHelper {

    public static CorsConfigurationSource getCorsConfigurationSourse(String corsWhiteList){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(corsWhiteList.split(",")));
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.addExposedHeader("X-CSRF-TOKEN");
        configuration.addExposedHeader("X-Redirect-Location");
        configuration.addExposedHeader(HttpHeaders.CONTENT_DISPOSITION);
        configuration.addExposedHeader("X-Captcha");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}