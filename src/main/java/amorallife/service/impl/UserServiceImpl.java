package amorallife.service.impl;

import amorallife.dto.UserDto;
import amorallife.entity.Role;
import amorallife.entity.User;
import amorallife.entity.UserRole;
import amorallife.mapper.UserMapper;
import amorallife.repository.RoleRepository;
import amorallife.repository.UserRepository;
import amorallife.repository.UserRoleRepository;
import amorallife.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto register(User user, UserRole userRole) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        userRole.setUser(user);
        userRole.setRole(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        UserDto registeredUser = UserMapper.userToDto(userRepository.save(user));

        return registeredUser;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findByUsername(String name) {
        UserDto user = UserMapper.userToDto(userRepository.findByUsername(name));
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public UserRole findRoleById(Long id) {
        UserRole result = userRoleRepository.getOne(id);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        UserDto user = UserMapper.userToDto(userRepository.getOne(id));
        return user;
    }

    @Override
    @Transactional
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
}
