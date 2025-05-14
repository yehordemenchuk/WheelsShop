package org.wheelsshop.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.wheelsshop.services.JwtService;

import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        try {
            if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);

                String jwt = authorizationHeader.substring(7);

                String username = jwtService.extractUsername(jwt);

                if (Objects.nonNull(username) &&
                        Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if (jwtService.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                        token.setDetails(new WebAuthenticationDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(token);
                    }


                }
            }

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());

            return;
        }

        filterChain.doFilter(request, response);
    }
}
