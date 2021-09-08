package amorallife.service.impl;

import amorallife.dto.AuthenticationRequestDto;
import amorallife.entity.Role;
import amorallife.entity.User;
import amorallife.entity.UserRole;
import amorallife.repository.RoleRepository;
import amorallife.repository.UserRoleRepository;
import amorallife.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import amorallife.dto.UserDto;
import amorallife.mapper.UserMapper;
import amorallife.repository.UserRepository;
import amorallife.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(User user, UserRole userRole) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        userRole.setUser(user);
        userRole.setRole(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        User registeredUser = userRepository.save(user);

        return registeredUser;
    }

    @Override
    public User findByUsername(String name) {
        User result = userRepository.findByUsername(name);
        return result;
    }

    @Override
    public UserRole findRoleById(Long id) {
        UserRole result = userRoleRepository.getOne(id);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> login(AuthenticationRequestDto authenticationRequestDto) {
        try {
            String username = authenticationRequestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequestDto.getPassword()));
            User user = findByUsername(username);

            if (user == null){
                throw new UsernameNotFoundException("User: " + username + " not found");
            }

            UserRole userRole = findRoleById(user.getId());
            String token = jwtTokenProvider.createToken(username, (List<Role>) userRole);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);

        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
