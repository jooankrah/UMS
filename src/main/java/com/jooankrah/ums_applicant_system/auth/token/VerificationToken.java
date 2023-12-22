package com.jooankrah.ums_applicant_system.auth.token;

import com.jooankrah.ums_applicant_system.applicant.Applicant;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String token;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "applicantId"
    )
    private Applicant applicant;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
