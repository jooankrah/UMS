package com.jooankrah.ums_applicant_system.auth.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    public void saveToken(VerificationToken token) {
        this.verificationTokenRepository.save(token);
    }


}
