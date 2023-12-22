package com.jooankrah.ums_applicant_system;

import com.jooankrah.ums_applicant_system.role.Role;
import com.jooankrah.ums_applicant_system.role.RoleName;
import com.jooankrah.ums_applicant_system.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jooankrah.ums_applicant_system.applicant.Applicant;
import com.jooankrah.ums_applicant_system.applicant.ApplicantRepository;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
	@Autowired
	private final ApplicantRepository applicantRepository;

	@Autowired
	private final RoleRepository roleRepository;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
        applicantRepository.deleteAll();
		roleRepository.deleteAll();
		Role role = this.roleRepository.save(new Role("",RoleName.ADMIN));
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		Applicant.ApplicantBuilder builder = Applicant.builder();
		builder.email("jooankrah@gmail.com");
		builder.password(passwordEncoder.encode("12345678"));
		builder.roles(roles);
		Applicant newApplicant = builder
		.build();

		log.info("adding new applicant " + newApplicant);

		applicantRepository.save(newApplicant);
	}

}