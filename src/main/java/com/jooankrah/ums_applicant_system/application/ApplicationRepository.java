package com.jooankrah.ums_applicant_system.application;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String> {
    
    /**
     * @param  String applicantID
     * @return Application application for provided applicant ID
     */
    @Query("SELECT a FROM Application a WHERE a.applicant.id = ?1")
    Optional<Application> findApplicationByApplicantID(String applicantID);
}
