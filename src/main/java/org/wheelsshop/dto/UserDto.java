package org.wheelsshop.dto;

import org.wheelsshop.entities.Role;
import org.wheelsshop.entities.User;

public record UserDto(Long id,
                      String name,
                      String surname,
                      String email,
                      Role role) {
}
