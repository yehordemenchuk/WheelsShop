package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.wheelsshop.dto.UserDTO;
import org.wheelsshop.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO mapToUserDTO(User user);

    User mapToUser(UserDTO userDTO);
}
