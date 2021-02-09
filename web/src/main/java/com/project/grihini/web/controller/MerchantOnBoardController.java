package com.project.grihini.web.controller;

import com.project.grihini.onboarding.model.FormData;
import com.project.grihini.onboarding.service.CookOnBoardingService;
import com.project.grihini.web.dto.OnboardingResponseDto;
import com.project.grihini.web.service.OnboardingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static com.project.grihini.web.util.Constants.*;
import javax.validation.Valid;


@RestController
@Slf4j
public class MerchantOnBoardController {

  @Autowired
  private CookOnBoardingService cookOnBoardingService;

  @Autowired
  private OnboardingService onboardingService;

  @PutMapping(value = ONBOARD_COOK, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity cookOnBoard(@RequestBody @Valid FormData formData, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      log.info("request ended with error");
      return new ResponseEntity<>(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
    }
    return onboardingService.onBoardMerchant(formData);
  }


}
