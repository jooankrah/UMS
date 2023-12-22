package com.jooankrah.ums_applicant_system.auth;

import org.jetbrains.annotations.NotNull;

import com.jooankrah.ums_applicant_system.auth.dtos.LoginRequestDTO;
import com.jooankrah.ums_applicant_system.auth.dtos.LoginResponseDTO;
import com.jooankrah.ums_applicant_system.auth.dtos.RegisterRequestDTO;

public interface IAuthService {

    /**
         * register new applicant with an email and password, then logs in applicant
         * @param registerRequest register request body
         * @return LoginResponseDTO
         */
    LoginResponseDTO register(RegisterRequestDTO registerRequest);

    /**
         * login in an applicant with email and password
         * @param loginRequestBody request data to log in an applicant
         * @return LoginResponse
         */
    LoginResponseDTO login(LoginRequestDTO loginRequestBody);

    void verifyAccount(String email);

    void resetPassword();

}