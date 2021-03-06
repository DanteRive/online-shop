package amorallife.dto;

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
    private String password;
    private boolean active;

}
