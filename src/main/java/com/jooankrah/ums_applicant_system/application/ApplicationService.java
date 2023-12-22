package com.jooankrah.ums_applicant_system.application;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jooankrah.ums_applicant_system.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService implements IApplicationService {

    private final ApplicationRepository applicationRepository;

    /**
     * Accepts a string of applicant ID and returns a list of applications sorted in
     * descending order
     * 
     * @param applicantID
     * @return List of all applications for a given user/applicant
     */
    @Override
    public List<Application> geApplicationsByApplicantID(String applicantID) {
        Application application = new Application();
        application.getApplicant().setId(applicantID);

        Example<Application> applicationExample = Example.of(application);

        return this.applicationRepository.findAll(applicationExample, Sort.by(Direction.DESC, "DateOfEntry"));
    }

    /**
     * Accepts a string of applicant ID and returns a list of applications sorted in
     * descending order
     * 
     * @param applicantID
     * @param applicationID
     * @return application
     * @throws NotfoundException
     */
    @Override
    public Application getApplicationforApplicant(String applicantID, String applicationID) {
        Application application = new Application();
        application.setId(applicationID);
        application.getApplicant().setId(applicantID);

        Example<Application> example = Example.of(application);

        return this.applicationRepository.findOne(example)
                .orElseThrow(
                        () -> new NotFoundException("No Application found for applicant with ID : " + applicantID));
    };

    /**
     * 
     * @param applicationID
     * @param updateBody
     * @return Application
     */
    @Override
    public Application editApplication(String applicationID, Object updateBody) {
        var application = this.applicationRepository.findById(applicationID)
                .orElseThrow(
                        () -> new NotFoundException("Application not found"));
        return this.applicationRepository.save(application);
    }
}
