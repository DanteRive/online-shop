package amorallife.service;

import amorallife.dto.AuthenticationRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    void login(AuthenticationRequestDto authenticationRequestDto);

    void logout(HttpServletRequest request, HttpServletResponse response);

}
