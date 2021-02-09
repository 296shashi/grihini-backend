package com.project.grihini.web.service;

import com.project.grihini.core.onboarding.enums.OnboardingStatus;
import com.project.grihini.onboarding.enums.OnboardingStateHTTP;
import com.project.grihini.onboarding.model.FormData;
import com.project.grihini.onboarding.model.OnBoardingWorkflowBean;
import com.project.grihini.onboarding.service.CookOnBoardingService;
import com.project.grihini.web.dto.OnboardingResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OnboardingService {

  @Autowired
  private CookOnBoardingService cookOnBoardingService;

  public ResponseEntity onBoardMerchant(FormData formData) {

    try {
      OnBoardingWorkflowBean workflowBean = cookOnBoardingService.processCookOnBoarding(formData);
      log.info("Sending response : {}", workflowBean.getOnBoardingResponseDTO());
      HttpStatus httpStatus = workflowBean.getOnBoardingResponseDTO().getStatus().getStatus();
      return new ResponseEntity(workflowBean.getOnBoardingResponseDTO(), httpStatus);
    } catch (Exception e){
      log.error("Something went wrong : {}", e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  public boolean onboardingInit() {
    return true;
  }

  public boolean onboardingProcessing() {
    return true;
  }

  public boolean onboardingCheckForExist() {
    return true;
  }

  public boolean onboardingProcessPending() {
    return true;
  }

  public boolean onboardingProcessApproved() {
    return true;
  }

  public boolean onboardingCompletion() {
    return true;
  }

}
