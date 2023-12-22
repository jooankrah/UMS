package com.jooankrah.ums_applicant_system.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDTO {

    @NotBlank
    @Email
     private String email;

    @NotBlank
    @Size(min = 8, message = "Password shouldn't be less than eight characters")
     private String password;

    @NotBlank
     private String confirmPassword;
}
