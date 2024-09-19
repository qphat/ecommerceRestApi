package com.example.ecommerce.controller;

import com.example.ecommerce.model.dto.JWTAuthResponse;
import com.example.ecommerce.model.dto.LoginDTO;
import com.example.ecommerce.model.dto.RegisterDTO;
import com.example.ecommerce.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService iAuthService;

    @PostMapping(value = {"/login", "/sign-in"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDTO) {
        String token = iAuthService.login(loginDTO);

        JWTAuthResponse jwtAuthResponse =  new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping(value = {"/register", "/sign-up"})
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        String response = iAuthService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
