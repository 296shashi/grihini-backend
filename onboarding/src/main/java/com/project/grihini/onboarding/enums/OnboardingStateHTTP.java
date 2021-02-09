package com.project.grihini.onboarding.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum OnboardingStateHTTP {

  INIT(1, HttpStatus.ACCEPTED),
  PENDING(2, HttpStatus.ACCEPTED),
  APPROVED(3, HttpStatus.ACCEPTED),
  PRE_ACTIVE(4, HttpStatus.ACCEPTED),
  ACTIVE(5, HttpStatus.CREATED),
  ALREADY_REGISTERED(6, HttpStatus.ALREADY_REPORTED);

  private Integer state;
  private HttpStatus status;

}
