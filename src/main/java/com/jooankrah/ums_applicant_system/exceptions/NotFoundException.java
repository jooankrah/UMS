package com.jooankrah.ums_applicant_system.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {
   private final String message;
}
