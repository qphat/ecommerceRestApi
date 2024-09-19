package com.example.ecommerce;

import com.example.ecommerce.controller.AuthController;
import com.example.ecommerce.model.dto.JWTAuthResponse;
import com.example.ecommerce.model.dto.LoginDTO;
import com.example.ecommerce.model.dto.RegisterDTO;
import com.example.ecommerce.service.IAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private IAuthService iAuthService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsernameOrEmail("testuser");
        loginDTO.setPassword("password");

        String token = "dummyToken";
        when(iAuthService.login(loginDTO)).thenReturn(token);

        ResponseEntity<JWTAuthResponse> response = authController.login(loginDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody().getAccessToken());
    }

    @Test
    void testRegister() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("newuser");
        registerDTO.setPassword("password");

        String expectedResponse = "User registered successfully";
        when(iAuthService.register(registerDTO)).thenReturn(expectedResponse);

        ResponseEntity<String> response = authController.register(registerDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}
