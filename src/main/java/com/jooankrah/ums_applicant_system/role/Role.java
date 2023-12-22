package com.jooankrah.ums_applicant_system.role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated( EnumType.STRING)
    RoleName roleName;

    public String getRoleName() {
        return this.roleName.toString();
    }
}
