package amorallife.controller;

import amorallife.dto.AuthenticationRequestDto;
import amorallife.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth/")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("login")
    public void login(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        authenticationService.login(authenticationRequestDto);
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        authenticationService.logout(request, response);
    }

}
