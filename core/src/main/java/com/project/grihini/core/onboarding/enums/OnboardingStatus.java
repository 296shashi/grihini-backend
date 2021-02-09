package com.project.grihini.core.onboarding.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum OnboardingStatus {

  INIT(1),
  PENDING(2),
  APPROVED(3),
  REJECTED(4),
  BLACKLIST(5);

  private Integer state;

}
