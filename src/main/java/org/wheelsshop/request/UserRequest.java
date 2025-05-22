package org.wheelsshop.request;

import org.wheelsshop.entities.Role;
import org.wheelsshop.entities.User;

public record UserRequest(String name,
                          String surname,
                          String email,
                          String hashPassword,
                          Role role) implements Request<User>{
}
