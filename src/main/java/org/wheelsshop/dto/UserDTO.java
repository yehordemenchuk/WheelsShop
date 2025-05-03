package org.wheelsshop.dto;

import lombok.Data;
import org.wheelsshop.entities.Role;

@Data
public class UserDTO {
    private final String name;

    private final String surname;

    private final String email;

    private final String hashPassword;

    private final Role role;
}
