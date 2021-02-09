package com.project.grihini.onboarding.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.grihini.core.onboarding.entity.AddressEntity;
import com.project.grihini.core.onboarding.entity.BasicDetailsEntity;
import com.project.grihini.core.onboarding.entity.MerchantInfoEntity;
import com.project.grihini.core.onboarding.entity.OnBoardingDataEntity;
import com.project.grihini.core.onboarding.enums.OnboardingStatus;
import com.project.grihini.core.onboarding.mapper.AddressMapper;
import com.project.grihini.core.onboarding.mapper.BasicDetailMapper;
import com.project.grihini.core.onboarding.mapper.MerchantInfoMapper;
import com.project.grihini.core.onboarding.mapper.OnBoardingDataMapper;
import com.project.grihini.core.onboarding.repo.AddressRepo;
import com.project.grihini.core.onboarding.repo.BasicInfoRepo;
import com.project.grihini.core.onboarding.repo.MerchantInfoRepo;
import com.project.grihini.core.onboarding.repo.OnBoardingDataRepo;
import com.project.grihini.onboarding.enums.OnboardingStateHTTP;
import com.project.grihini.onboarding.mapper.OnBoardingEntityMapper;
import com.project.grihini.onboarding.model.FormData;
import com.project.grihini.onboarding.model.OnBoardingResponseDTO;
import com.project.grihini.onboarding.model.OnBoardingWorkflowBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class CookOnBoardingService {

  @Autowired
  private OnBoardingDataRepo onBoardingDataRepo;

  @Autowired
  private BasicInfoRepo basicInfoRepo;

  @Autowired
  private AddressRepo addressRepo;

  @Autowired
  private MerchantInfoRepo merchantInfoRepo;

  @Autowired
  private OnBoardingEntityMapper onBoardingRequestMapper;

  public static final String ONBOARDING_SUCCESS_MSG = "You are successfully onboarded to Grihini!.You will get confirmation mail after approval";
  public static final String ONBOARDING_FAILURE_MSG = "You are already registered with us";
  private static ObjectMapper objectMapper = new ObjectMapper();

  public OnBoardingWorkflowBean processCookOnBoarding(FormData formData) throws RuntimeException{

    OnBoardingWorkflowBean workflowBean = new OnBoardingWorkflowBean();
    workflowBean.setFormData(formData);
    saveOnBoardingRequest(workflowBean);
    return workflowBean;
  }

  private void saveOnBoardingRequest(OnBoardingWorkflowBean workflowBean) throws RuntimeException{
    OnBoardingDataEntity onBoardingDataEntity = new OnBoardingDataEntity();
    try {
      onBoardingDataEntity.setRequest(objectMapper.writeValueAsString(workflowBean.getFormData()));
    } catch (JsonProcessingException e) {
      log.info("Exception occurred : {}", e);
      throw new RuntimeException();
    }

    onBoardingDataEntity.setStatus(OnboardingStatus.INIT);
    onBoardingDataEntity = onBoardingDataRepo.save(onBoardingDataEntity);
    workflowBean.setOnBoardingDataBO(OnBoardingDataMapper.INSTANCE.entityToBo(onBoardingDataEntity));

    OnBoardingResponseDTO responseDTO = new OnBoardingResponseDTO();
    if (checkForExistingUser(workflowBean)) {

      saveBasicInfo(workflowBean);
      saveAddressInfo(workflowBean);
      saveMerchantInfo(workflowBean);

      onBoardingDataEntity.setStatus(OnboardingStatus.PENDING);
      onBoardingDataEntity = onBoardingDataRepo.save(onBoardingDataEntity);
      workflowBean.setOnBoardingDataBO(OnBoardingDataMapper.INSTANCE.entityToBo(onBoardingDataEntity));

      responseDTO.setMessage(ONBOARDING_SUCCESS_MSG);
      responseDTO.setStatus(OnboardingStateHTTP.PENDING);

    }
    else
    {
      responseDTO.setMessage(ONBOARDING_FAILURE_MSG);
      responseDTO.setStatus(OnboardingStateHTTP.ALREADY_REGISTERED);
    }
    workflowBean.setOnBoardingResponseDTO(responseDTO);
  }


  private void saveBasicInfo(OnBoardingWorkflowBean workflowBean) {
    BasicDetailsEntity basicDetailsEntity = null;
    try {
      basicDetailsEntity = onBoardingRequestMapper.mapFormToBasicDetails(workflowBean.getFormData());
      basicDetailsEntity = basicInfoRepo.save(basicDetailsEntity);
    } catch (Exception e) {
      log.error("Basic Details failed to save {}",e.getMessage());
    }
    workflowBean.setBasicInfoBo(BasicDetailMapper.INSTANCE.entityToBo(basicDetailsEntity));
  }

  private void saveAddressInfo(OnBoardingWorkflowBean workflowBean) {
    AddressEntity addressEntity = null;
    try {
      addressEntity = onBoardingRequestMapper.mapFormToAddress(workflowBean.getFormData());
      addressEntity = addressRepo.save(addressEntity);
    } catch (Exception e) {
      log.error("Address Details failed to save {}",e.getMessage());
    }
    workflowBean.setAddressBO(AddressMapper.INSTANCE.entityToBo(addressEntity));
  }

  private void saveMerchantInfo(OnBoardingWorkflowBean workflowBean) {
    MerchantInfoEntity merchantInfoEntity = null;
    try {
      merchantInfoEntity = onBoardingRequestMapper.mapFormToMerchantInfo(workflowBean);
      merchantInfoEntity = merchantInfoRepo.save(merchantInfoEntity);
    } catch (Exception e) {
      log.error("Merchant Details failed to save {}",e.getMessage());
    }
    workflowBean.setMerchantInfoBO(MerchantInfoMapper.INSTANCE.entityToBo(merchantInfoEntity));
  }

  private boolean checkForExistingUser(OnBoardingWorkflowBean workflowBean) {
    List<BasicDetailsEntity> basicDetailsEntity = basicInfoRepo.findBasicDetailsByEmailAndMobileNo(workflowBean.getFormData().getEmail(), workflowBean.getFormData().getMobileNo());
    if (CollectionUtils.isEmpty(basicDetailsEntity))
      return true;
    return false;
  }
}
