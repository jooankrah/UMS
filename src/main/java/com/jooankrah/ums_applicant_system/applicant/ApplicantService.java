package com.jooankrah.ums_applicant_system.applicant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jooankrah.ums_applicant_system.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicantService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    private final ApplicantRepository applicationRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        return applicationRepository.findApplicantByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Applicant not found for serial number: " + email));

    }

    public List<Applicant> getApplicants() {
        return this.applicationRepository.findAll();
    }

    public Applicant geApplicantByEmail(String email) {
        return this.applicationRepository.findApplicantByEmail(email)
                .orElseThrow(() -> new NotFoundException("Applicant with email " + email + " not found"));
    }

}
