package com.jooankrah.ums_applicant_system.result;

import com.jooankrah.ums_applicant_system.application.Application;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Result{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank @Past
    private String yearTaken;

    
    private String examType;

    @NotBlank @Size(min = 14)
    private String indexNumber;
}
