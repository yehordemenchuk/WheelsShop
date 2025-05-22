package org.wheelsshop.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wheelsshop.entities.User;
import org.wheelsshop.repository.jpa.UserJpaRepository;

import java.util.Collections;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;

    public UserDetailsServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJpaRepository.findByEmail(username);

        if (Objects.nonNull(user)) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getHashPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
            );
        }

        throw new UsernameNotFoundException(username);
    }
}
