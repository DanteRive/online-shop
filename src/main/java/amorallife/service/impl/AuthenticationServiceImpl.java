package amorallife.service.impl;

import amorallife.dto.AuthenticationRequestDto;
import amorallife.dto.UserDto;
import amorallife.entity.Role;
import amorallife.entity.UserRole;
import amorallife.mapper.UserMapper;
import amorallife.repository.UserRepository;
import amorallife.repository.UserRoleRepository;
import amorallife.security.jwt.JwtTokenProvider;
import amorallife.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    @Override
    @Transactional
    public void login(AuthenticationRequestDto authenticationRequestDto) {
        try {
            String username = authenticationRequestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequestDto.getPassword()));
            UserDto user = UserMapper.userToDto(userRepository.findByUsername(username));

            if (user == null){
                throw new UsernameNotFoundException("User: " + username + " not found");
            }

            UserRole userRole = userRoleRepository.findRoleById(user.getId());
            String token = jwtTokenProvider.createToken(username, (List<Role>) userRole);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);
    }


}
