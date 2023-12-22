package com.jooankrah.ums_applicant_system.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class LoginRequestDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
