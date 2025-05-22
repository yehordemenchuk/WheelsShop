package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.wheelsshop.dto.UserDto;
import org.wheelsshop.entities.User;
import org.wheelsshop.request.UserRequest;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User> {
    User fromUserRequest(UserRequest userRequest);

    UserDto toDto(User user);
}
