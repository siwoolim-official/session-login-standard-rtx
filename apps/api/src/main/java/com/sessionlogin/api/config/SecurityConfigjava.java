package com.sessionlogin.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration  // Config file setting
@EnableWebSecurity // Spring Security active
public class SecurityConfigjava {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        // cors 설정 적용
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // csrf 설정 임시 비활성화 테스트용
        http.csrf(AbstractHttpConfigurer::disable);

        // http 모든 접근 허용 테스트용
        http.authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // http://localhost:5173 에서 오는 요청만 허용
        config.setAllowedOrigins(List.of("http://localhost:5173"));

        // "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS" 메서드만 허용
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

        // 자격증명 허용
        config.setAllowCredentials(true);

        // 위 설정을 모든 URL 경로에 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

}
