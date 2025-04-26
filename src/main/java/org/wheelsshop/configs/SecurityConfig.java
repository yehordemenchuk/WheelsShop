package org.wheelsshop.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.wheelsshop.entities.Role;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, DefaultAuthenticationEventPublisher authenticationEventPublisher) throws Exception {
            return http.authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/v1/user/save").authenticated()
                            .requestMatchers("/api/v1/order/save").authenticated()
                            .requestMatchers("/api/v1/order/find/**").authenticated()
                            .requestMatchers("/api/v1/order/delete/**").authenticated()
                            .requestMatchers("/api/v1/car/save").hasRole(Role.ADMIN.name())
                            .requestMatchers("/api/v1/brand/save").hasRole(Role.ADMIN.name())
                            .requestMatchers("/api/v1/car/delete/**").hasRole(Role.ADMIN.name())
                            .requestMatchers("/api/v1/brand/delete/**").hasRole(Role.ADMIN.name())
                            .requestMatchers("/api/v1/user/delete/**").hasRole(Role.ADMIN.name())
                            .requestMatchers("/api/v1/user/all").hasRole(Role.ADMIN.name())
                            .requestMatchers("/api/v1/order/all").hasRole(Role.ADMIN.name())
                            .requestMatchers("/api/v1/user/find/**").hasRole(Role.ADMIN.name())
                            .requestMatchers("/api/v1/car/**").permitAll()
                            .requestMatchers("/api/v1/brand/**").permitAll()
                            .requestMatchers("/api/v1/home").permitAll()
                            .requestMatchers("/api/v1/order/**").authenticated())
                            .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                    .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
