package com.example.ecommerce.service.impl;


import com.example.ecommerce.exception.ECommerceAPIException;
import com.example.ecommerce.model.dto.LoginDTO;
import com.example.ecommerce.model.dto.RegisterDTO;
import com.example.ecommerce.model.entity.Role;
import com.example.ecommerce.model.entity.User;
import com.example.ecommerce.repository.IRoleRepository;
import com.example.ecommerce.repository.IUserRepository;
import com.example.ecommerce.security.JWTokenProvider;
import com.example.ecommerce.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class IAuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTokenProvider jwTokenProvider;


    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword())
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        return jwTokenProvider.generateToken(authentication);
    }


    @Override
    public String register(RegisterDTO registerDTO) {
        if (iUserRepository.existsByUsername(registerDTO.getUsername())) {
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }
        if (iUserRepository.existsByEmail(registerDTO.getEmail())) {
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = iRoleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ECommerceAPIException(HttpStatus.INTERNAL_SERVER_ERROR, "User Role not set."));
        roles.add(userRole);
        user.setRoles(roles);

        iUserRepository.save(user);

        return "User register successfully!";
    }
}
