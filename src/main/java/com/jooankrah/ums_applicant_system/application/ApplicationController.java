package com.jooankrah.ums_applicant_system.application;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final IApplicationService applicationService;

    @PostMapping("/create")
    @CrossOrigin
    public String createApplication(HttpServletRequest request) {
        System.out.println(request.getUserPrincipal().getName());
        return "";
    }
}
