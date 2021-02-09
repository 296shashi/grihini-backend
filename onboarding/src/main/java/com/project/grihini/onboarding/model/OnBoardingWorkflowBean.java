package com.project.grihini.onboarding.model;

import com.project.grihini.core.onboarding.bo.AddressBO;
import com.project.grihini.core.onboarding.bo.BasicInfoBo;
import com.project.grihini.core.onboarding.bo.MerchantInfoBO;
import com.project.grihini.core.onboarding.bo.OnBoardingDataBO;
import lombok.Data;

@Data
public class OnBoardingWorkflowBean {

  private FormData formData;
  private OnBoardingDataBO onBoardingDataBO;
  private AddressBO addressBO;
  private BasicInfoBo basicInfoBo;
  private MerchantInfoBO merchantInfoBO;
  private OnBoardingResponseDTO onBoardingResponseDTO;
}
