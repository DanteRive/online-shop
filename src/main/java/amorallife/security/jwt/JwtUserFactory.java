package amorallife.security.jwt;

import amorallife.dto.UserDto;
import amorallife.entity.Role;
import amorallife.entity.User;
import amorallife.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory(){
    }

    public static JwtUser create (UserDto user, UserRole userRole){
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.isActive(),
                mapToGrantedAuthorities((List<Role>) userRole.getRole())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
