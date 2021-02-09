package com.project.grihini.onboarding.model;

import com.project.grihini.core.onboarding.enums.OnboardingStatus;
import com.project.grihini.onboarding.enums.OnboardingStateHTTP;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class OnBoardingResponseDTO {

  private OnboardingStateHTTP status;
  private String message;
}
