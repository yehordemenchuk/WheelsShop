package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.wheelsshop.dto.UserDto;
import org.wheelsshop.entities.User;
import org.wheelsshop.request.UserRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto UserToDto(User user);

    User fromUserRequest(UserRequest request);
}
