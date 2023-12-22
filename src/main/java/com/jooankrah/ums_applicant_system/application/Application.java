package com.jooankrah.ums_applicant_system.application;

import java.time.ZonedDateTime;
import java.util.List;

import com.jooankrah.ums_applicant_system.applicant.Applicant;

import com.jooankrah.ums_applicant_system.result.Result;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Application")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Applicant Details
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "applicant_Id")
    private Applicant applicant;

    @NotBlank
    private String title;

    @NotBlank
    private String surname;

    @NotBlank
    private String otherNames;

    @NotBlank
    private String gender;

    @NotBlank
    private String maritalStatus;

    @NotBlank
    @Past
    private String dob;

    private String photo;

    @NotNull
    private boolean disability;

    // Contact Details
    @NotBlank
    private String country;

    @NotBlank
    private String region;

    @NotBlank
    private String homeTown;

    @NotBlank
    private String address1;

    @NotBlank
    private String address2;

    @NotBlank
    private String contact1;

    private String contact2;

    private String residentialStatus;

    // Academics
    @NotBlank
    private String studentType;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Result> results;

    @Valid
    private List<String> selectedPrograms;

    private String signature;

    private STATUS status;

    private ZonedDateTime dateOfEntry;

}
