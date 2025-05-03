package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.wheelsshop.dto.UserDTO;
import org.wheelsshop.entities.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO mapToUserDTO(User user);

    User mapToUser(UserDTO userDTO);
}
