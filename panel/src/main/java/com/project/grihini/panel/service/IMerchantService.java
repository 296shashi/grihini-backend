package com.project.grihini.panel.service;

import com.project.grihini.core.onboarding.bo.MerchantInfoBO;
import com.project.grihini.core.onboarding.bo.OnBoardingDataBO;
import com.project.grihini.core.onboarding.entity.MerchantInfoEntity;
import com.project.grihini.panel.dto.MerchantDetailsDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMerchantService {

  ResponseEntity fetchMerchantDetails(String merchantId);
  ResponseEntity fetchMerchantDetailsOnStatus(String status);
  ResponseEntity handleMerchant(String merchandId,String operation);
  ResponseEntity fetchAllMerchantDetails();

}
