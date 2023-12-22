package com.jooankrah.ums_applicant_system.application;

import java.util.List;

public interface IApplicationService {

    /**
     * Accepts a string of applicant ID and returns a list of applications sorted in
     * descending order
     * 
     * @param applicantID
     * @return List of all applications for a given user/applicant
     */
    List<Application> geApplicationsByApplicantID(String applicantID);

    /**
     * Accepts a string of applicant ID and returns a list of applications sorted in
     * descending order
     * 
     * @param applicantID
     * @param applicationID
     * @return the found application
     * @throws NotfoundException
     */
    Application getApplicationforApplicant(String applicantID, String applicationID);

    /**
     * 
     * @param applicationID
     * @param updateBody
     * @return Application
     */
    Application editApplication(String applicationID, Object updateBody);

}