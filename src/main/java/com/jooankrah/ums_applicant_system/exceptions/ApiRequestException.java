package com.jooankrah.ums_applicant_system.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiRequestException extends RuntimeException {
    private String message;
}
