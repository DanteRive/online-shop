package amorallife.service;

import amorallife.dto.AuthenticationRequestDto;
import amorallife.dto.UserDto;
import amorallife.entity.User;
import amorallife.entity.UserRole;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    User register(User user, UserRole userRolerole);

    User findByUsername(String name);

    UserRole findRoleById(Long id);

    User findById(Long id);

    void delete(Long id);

    List<UserDto> getAllUsers();

    ResponseEntity<?> login(AuthenticationRequestDto authenticationRequestDto);


}
