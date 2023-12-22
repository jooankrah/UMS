package com.jooankrah.ums_applicant_system.auth;

import com.jooankrah.ums_applicant_system.applicant.Applicant;
import com.jooankrah.ums_applicant_system.auth.dtos.RegisterRequestDTO;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jooankrah.ums_applicant_system.auth.dtos.LoginRequestDTO;
import com.jooankrah.ums_applicant_system.auth.dtos.LoginResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final IAuthService authService;

    @PostMapping("/login")
    @CrossOrigin()
    public ResponseEntity<LoginResponseDTO> loginController (@Valid @RequestBody LoginRequestDTO loginRequest) {
       LoginResponseDTO response = this.authService.login(loginRequest);
       return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO>  register(@Valid @RequestBody RegisterRequestDTO registerRequest){
        LoginResponseDTO response = this.authService.register(registerRequest);
        return ResponseEntity.status(201).body(response);
    }
}
