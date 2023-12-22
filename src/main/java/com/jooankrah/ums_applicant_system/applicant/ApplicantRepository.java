package com.jooankrah.ums_applicant_system.applicant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, String>{
    Optional<Applicant> findApplicantByEmail(String email);
}
