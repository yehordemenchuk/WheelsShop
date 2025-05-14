package org.wheelsshop.configs;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.wheelsshop.entities.Role;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private static final String ADMIN_ROLE_NAME = Role.ADMIN.name();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   DefaultAuthenticationEventPublisher authenticationEventPublisher) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(
                                "/api/v1/user/save",
                                "/api/v1/cars/**",
                                "/api/v1/brand/**",
                                "/api/v1/home/**"
                        ).permitAll()
                                .requestMatchers(
                                        "/api/v1/order/save",
                                        "/api/v1/order/find/**",
                                        "/api/v1/order/**"
                                ).authenticated()
                                .requestMatchers(
                                        "/api/v1/car/save",
                                        "/api/v1/brand/save",
                                        "/api/v1/car/delete/**",
                                        "/api/v1/brand/delete/**",
                                        "/api/v1/user/delete/**",
                                        "/api/v1/user/all",
                                        "/api/v1/order/all",
                                        "/api/v1/user/find/**"
                                ).hasRole(ADMIN_ROLE_NAME)

                                .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {})
                .exceptionHandling(
                        exception ->
                                exception.authenticationEntryPoint(
                                        (req,
                                         res, ex) ->
                                        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage()))
                                        .accessDeniedHandler(
                                                (req,
                                                 res, ex) ->
                                                        res.sendError(HttpServletResponse.SC_FORBIDDEN,
                                                                ex.getMessage())
                                        )
                );


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(List.of("*"));

        corsConfiguration.setAllowedHeaders(List.of("*"));

        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}