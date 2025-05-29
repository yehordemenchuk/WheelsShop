package org.wheelsshop.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.wheelsshop.entities.Role;
import org.wheelsshop.security.LoginAndPasswordFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private static final String ADMIN_ROLE_NAME = Role.ADMIN.name();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationManager authenticationManager) throws Exception {

        LoginAndPasswordFilter loginAndPasswordFilter = new LoginAndPasswordFilter(authenticationManager);

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(
                                        "/api/v1/cars/save",
                                        "/api/v1/brands/save",
                                        "/api/v1/cars/delete/**",
                                        "/api/v1/brand/delete/**",
                                        "/api/v1/users/all",
                                        "/api/v1/orders/all",
                                        "/api/v1/users/find/**"
                                ).hasRole(ADMIN_ROLE_NAME)
                                .requestMatchers(
                                        "/api/v1/orders/save",
                                        "/api/v1/orders/find/**",
                                        "/api/v1/orders/**"
                                ).authenticated()
                                .requestMatchers(
                                        "/api/v1/users/save",
                                        "/api/v1/users/delete/**",
                                        "/api/v1/cars/**",
                                        "/api/v1/brands/**"
                                ).permitAll()
                )
                .addFilterBefore(loginAndPasswordFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}