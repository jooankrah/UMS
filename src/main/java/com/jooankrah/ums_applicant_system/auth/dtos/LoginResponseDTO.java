package com.jooankrah.ums_applicant_system.auth.dtos;

import com.jooankrah.ums_applicant_system.applicant.Applicant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class LoginResponseDTO {
    private String email;
    private String accessToken;
    private String refreshToken;
}
