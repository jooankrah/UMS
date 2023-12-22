package com.jooankrah.ums_applicant_system.auth;

import com.jooankrah.ums_applicant_system.auth.dtos.RegisterRequestDTO;
import com.jooankrah.ums_applicant_system.auth.token.VerificationToken;
import com.jooankrah.ums_applicant_system.auth.token.VerificationTokenService;
import com.jooankrah.ums_applicant_system.exceptions.ApiRequestException;
import com.jooankrah.ums_applicant_system.role.Role;
import com.jooankrah.ums_applicant_system.role.RoleName;
import com.jooankrah.ums_applicant_system.role.RoleRepository;
import com.jooankrah.ums_applicant_system.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jooankrah.ums_applicant_system.applicant.Applicant;
import com.jooankrah.ums_applicant_system.applicant.ApplicantRepository;
import com.jooankrah.ums_applicant_system.auth.dtos.LoginRequestDTO;
import com.jooankrah.ums_applicant_system.auth.dtos.LoginResponseDTO;
import com.jooankrah.ums_applicant_system.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class AuthService implements IAuthService {
        private final ApplicantRepository applicantRepository;
        private final RoleRepository roleRepository;
        private final VerificationTokenService verificationTokenService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final JwtUtils jwtUtils;

        /**
         * register new applicant with an email and password, then logs in applicant
         * 
         * @param registerRequest register request body
         * @return LoginResponseDTO
         */
        @Override
        public LoginResponseDTO register(@NotNull RegisterRequestDTO registerRequest) {
                log.info("Creating new applicant");
                try {
                        if (this.applicantRepository.findApplicantByEmail(registerRequest.getEmail()).isPresent())
                                throw new ApiRequestException("Account already exists");

                        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword()))
                                throw new ApiRequestException("Passwords do not match");

                        String hashedPassword = this.passwordEncoder.encode(registerRequest.getPassword());
                        final Role defaultRole = this.roleRepository.findByRoleName(RoleName.ADMIN).orElseThrow(
                                        () -> new NotFoundException("Role \"ADMIN\" not found"));
                        List<Role> roles = new ArrayList<>();
                        roles.add(defaultRole);

                        Applicant applicant = Applicant.builder()
                                        .email(registerRequest.getEmail()).password(hashedPassword).roles(roles)
                                        .build();

                        this.applicantRepository.save(applicant);

                        String token = UUID.randomUUID().toString();
                        VerificationToken newToken = VerificationToken.builder()
                                        .token(token)
                                        .applicant(applicant)
                                        .createdAt(LocalDateTime.now())
                                        .expiresAt(LocalDateTime.now().plusHours(1))
                                        .build();
                        this.verificationTokenService.saveToken(newToken);

                        List<String> roleNames = new ArrayList<>();
                        applicant.getRoles().forEach(role -> roleNames.add(role.getRoleName()));
                        String accessToken = jwtUtils.generateToken(applicant.getUsername(), roleNames);

                        return new LoginResponseDTO(applicant.getUsername(), accessToken, accessToken);
                } catch (Exception e) {
                        throw new ApiRequestException(e.getMessage());
                }

        }

        /**
         * login in an applicant with email and password
         * 
         * @param loginRequestBody request data to log in an applicant
         * @return LoginResponse
         */
        @Override
        public LoginResponseDTO login(@NotNull LoginRequestDTO loginRequestBody) {
                log.info(
                                "Attempting to login user " + loginRequestBody.getEmail());
                try {
                        Authentication authentication = authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                        loginRequestBody.getEmail(),
                                                        loginRequestBody.getPassword()));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        Applicant applicant = this.applicantRepository.findApplicantByEmail(authentication.getName())
                                        .orElseThrow(
                                                        () -> new NotFoundException("Applicant with email "
                                                                        + authentication.getName() + "not found"));
                        List<String> roleNames = new ArrayList<>();
                        applicant.getRoles().forEach(role -> roleNames.add(role.getRoleName()));
                        String accessToken = jwtUtils.generateToken(applicant.getUsername(), roleNames);

                        return new LoginResponseDTO(applicant.getUsername(), accessToken, accessToken);
                } catch (Exception e) {
                        throw new ApiRequestException(e.getMessage());
                }
        }

        

        @Override
        public void verifyAccount(String email) {
                // todo - send reset password mail
        }

        @Override
        public void resetPassword() {

        }
}
