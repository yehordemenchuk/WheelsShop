package org.wheelsshop.mapper;

import org.mapstruct.Mapper;
import org.wheelsshop.dto.UserDto;
import org.wheelsshop.entities.User;
import org.wheelsshop.request.UserRequest;

@Mapper(componentModel = "spring")
public interface UserMapper extends MapperContract<UserDto, User, UserRequest>{
    @Override
    UserDto toDto(User user);

    @Override
    User fromRequest(UserRequest request);

    @Override
    User fromDto(UserDto userDto);
}
