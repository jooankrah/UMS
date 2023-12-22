package com.jooankrah.ums_applicant_system.auth;

import com.jooankrah.ums_applicant_system.exceptions.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;

import com.jooankrah.ums_applicant_system.applicant.Applicant;
import com.jooankrah.ums_applicant_system.applicant.ApplicantRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicantDetailPasswordService implements UserDetailsPasswordService{

    private final ApplicantRepository applicantRepository;
    
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        Applicant applicant = this.applicantRepository.findApplicantByEmail(user.getUsername()).orElseThrow(
                () -> new NotFoundException("No Application found for applicant with ID :"  + user.getUsername())
        );
        applicant.setPassword(newPassword);
        return applicant;
    }
    
}
