package com.example.ecommerce.service;

import com.example.ecommerce.model.dto.LoginDTO;
import com.example.ecommerce.model.dto.RegisterDTO;

public interface IAuthService {
    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
