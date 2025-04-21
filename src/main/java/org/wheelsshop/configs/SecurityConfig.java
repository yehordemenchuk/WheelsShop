package org.wheelsshop.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.wheelsshop.services.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder, UserService userService) {
        UserDetails admin = User.builder().username("admin").password(encoder.encode("admin")).roles("ADMIN").build();
        UserDetails user = User.builder().username("user").password(encoder.encode("user")).roles("CUSTOMER").build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, DefaultAuthenticationEventPublisher authenticationEventPublisher) throws Exception {
            return http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/v1/user/save").authenticated()
                            .requestMatchers("/api/v1/order/save").authenticated()
                            .requestMatchers("/api/v1/order/find/**").authenticated()
                            .requestMatchers("/api/v1/order/delete/**").authenticated()
                            .requestMatchers("/api/v1/car/save").hasRole("ADMIN")
                            .requestMatchers("/api/v1/brand/save").hasRole("ADMIN")
                            .requestMatchers("/api/v1/**/delete/**").hasRole("ADMIN")
                            .requestMatchers("/api/v1/user/all").hasRole("ADMIN")
                            .requestMatchers("/api/v1/order/all").hasRole("ADMIN")
                            .requestMatchers("/api/v1/user/find/**").hasRole("ADMIN")
                            .requestMatchers("/api/v1/cars/**").permitAll()
                            .requestMatchers("/api/v1/brands/**").permitAll()
                            .requestMatchers("/api/v1/order/**").authenticated())
                            .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                    .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // посмотри главный контроллер
}
