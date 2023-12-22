package com.jooankrah.ums_applicant_system.applicant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jooankrah.ums_applicant_system.application.Application;
import com.jooankrah.ums_applicant_system.role.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Applicant implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // auth credentials
    @NotBlank @Email
    @Column(unique = true)
    private String email;

    @NotBlank 
    private String password;

    @ManyToMany(fetch = FetchType.EAGER  , cascade = CascadeType.MERGE)
    private List<Role> roles;

    @OneToOne(mappedBy = "applicant", orphanRemoval = true)
    private Application application;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        this.roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
