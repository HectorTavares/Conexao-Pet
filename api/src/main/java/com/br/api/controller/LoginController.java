package com.br.api.controller;

import com.br.api.domain.response.LoginResponse;
import com.br.api.service.security.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginUserService loginUserService;

    @PostMapping("/public")
    public LoginResponse login() {
        return loginUserService.login();
    }
}
