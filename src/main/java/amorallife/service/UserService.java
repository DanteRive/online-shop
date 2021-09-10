package amorallife.service;

import amorallife.dto.AuthenticationRequestDto;
import amorallife.dto.UserDto;
import amorallife.entity.User;
import amorallife.entity.UserRole;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    UserDto register(User user, UserRole userRolerole);

    UserDto findByUsername(String name);

    UserRole findRoleById(Long id);

    UserDto findById(Long id);

    void delete(Long id);

    List<UserDto> getAllUsers();
}
