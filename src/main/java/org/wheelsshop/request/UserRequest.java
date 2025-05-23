package org.wheelsshop.request;

import org.wheelsshop.entities.Role;

public record UserRequest(String name,
                          String surname,
                          String email,
                          String hashPassword,
                          Role role){
}
