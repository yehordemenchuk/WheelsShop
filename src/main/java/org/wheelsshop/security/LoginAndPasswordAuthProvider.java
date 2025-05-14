package org.wheelsshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.wheelsshop.entities.Role;
import org.wheelsshop.entities.User;
import org.wheelsshop.exceptions.EntityNotFoundException;
import org.wheelsshop.repository.jpa.UserJpaRepository;

import java.util.List;

@Component
public class LoginAndPasswordAuthProvider implements AuthenticationProvider {
    private final UserJpaRepository userJpaRepository;

    @Autowired
    public LoginAndPasswordAuthProvider(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    private List<GrantedAuthority> getAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userJpaRepository.findByEmail(login);

        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }

        if (!password.equals(user.getHashPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        return new UsernamePasswordAuthenticationToken(login, password,
                getAuthorities(user.getRole()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
