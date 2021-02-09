package com.project.grihini.panel.dto;

import com.project.grihini.core.onboarding.bo.AddressBO;
import com.project.grihini.core.onboarding.bo.BasicInfoBo;
import com.project.grihini.core.onboarding.bo.MerchantInfoBO;
import com.project.grihini.core.onboarding.model.MerchantInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class MerchantDetailsDTO {

  private AddressBO addressBO;
  private BasicInfoBo basicInfoBo;
  private MerchantInfoBO merchantInfoBO;

}
