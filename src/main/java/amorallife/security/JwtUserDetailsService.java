package amorallife.security;

import amorallife.dto.UserDto;
import amorallife.entity.User;
import amorallife.entity.UserRole;
import amorallife.security.jwt.JwtUser;
import amorallife.security.jwt.JwtUserFactory;
import amorallife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.findByUsername(username);
        UserRole userRole = userService.findRoleById(user.getId());
        if (user == null){
            throw new UsernameNotFoundException("User: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user, userRole);

        return jwtUser;
    }
}
