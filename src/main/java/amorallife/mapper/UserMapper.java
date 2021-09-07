package amorallife.mapper;

import amorallife.dto.UserDto;
import amorallife.entity.User;

public class UserMapper {

    public static UserDto userToDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setName(user.getUsername())
                .setEmail(user.getEmail());
    }
}
