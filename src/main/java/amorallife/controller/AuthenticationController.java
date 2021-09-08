package amorallife.controller;

import amorallife.dto.AuthenticationRequestDto;
import amorallife.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth/")
@AllArgsConstructor
public class AuthenticationController {

    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        return userService.login(authenticationRequestDto);
    }
}
