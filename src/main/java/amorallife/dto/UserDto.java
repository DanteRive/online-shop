package amorallife.dto;

import amorallife.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class UserDto {

    private Long id;
    private String name;
    private String email;

}
