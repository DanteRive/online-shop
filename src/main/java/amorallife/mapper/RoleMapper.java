package amorallife.mapper;

import amorallife.dto.RoleDto;
import amorallife.entity.Role;

public class RoleMapper {
    public static RoleDto roleToDto(Role role){
        return new RoleDto()
                .setName(role.getName());
    }
}
