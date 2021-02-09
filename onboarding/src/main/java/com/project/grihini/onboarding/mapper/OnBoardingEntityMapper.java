package com.project.grihini.onboarding.mapper;

import com.project.grihini.core.onboarding.entity.AddressEntity;
import com.project.grihini.core.onboarding.entity.BasicDetailsEntity;
import com.project.grihini.core.onboarding.entity.MerchantInfoEntity;
import com.project.grihini.core.onboarding.entity.OnBoardingDataEntity;
import com.project.grihini.core.onboarding.enums.OnboardingStatus;
import com.project.grihini.core.onboarding.model.enums.AddressStatus;
import com.project.grihini.core.onboarding.model.enums.PanelUserStatus;
import com.project.grihini.core.onboarding.repo.AddressRepo;
import com.project.grihini.core.onboarding.repo.BasicInfoRepo;
import com.project.grihini.core.onboarding.repo.OnBoardingDataRepo;
import com.project.grihini.onboarding.model.FormData;
import com.project.grihini.onboarding.model.OnBoardingWorkflowBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OnBoardingEntityMapper {

    @Autowired
    private BasicInfoRepo basicInfoRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private OnBoardingDataRepo onBoardingDataRepo;

    public BasicDetailsEntity mapFormToBasicDetails(FormData formData)
    {
        BasicDetailsEntity basicDetails = new BasicDetailsEntity();
        basicDetails.setFirstName(formData.getFirstName());
        basicDetails.setLastName(formData.getLastName());
        basicDetails.setMobileNo(formData.getMobileNo());
        basicDetails.setEmail(formData.getEmail());
        basicDetails.setGender(formData.getGender());
        basicDetails.setDob(formData.getDob());
        basicDetails.setImgUrl("testurl");
        basicDetails.setDocumentUrl("testdocurl");
        basicDetails.setStatus(PanelUserStatus.INACTIVE);
        return basicDetails;
    }

    public AddressEntity mapFormToAddress(FormData formData)
    {
        AddressEntity address=new AddressEntity();
        address.setAddressLine1(formData.getAddressLine1());
        address.setAddressLine2(formData.getAddressLine2());
        address.setCity(formData.getCity());
        address.setState(formData.getState());
        address.setPinCode(formData.getPinCode());
        address.setLatitude(formData.getLatitude());
        address.setLongitude(formData.getLatitude());
        address.setStatus(AddressStatus.INACTIVE);
        return address;
    }

    public MerchantInfoEntity mapFormToMerchantInfo(OnBoardingWorkflowBean workflowBean)
    {
        MerchantInfoEntity merchantInfo=new MerchantInfoEntity();
        merchantInfo.setMerchantId(UUID.randomUUID().toString());
        List<BasicDetailsEntity> basicDetailsEntity = basicInfoRepo.findBasicDetailsByEmailAndMobileNo(workflowBean.getFormData().getEmail(), workflowBean.getFormData().getMobileNo());
        if (CollectionUtils.isEmpty(basicDetailsEntity))
        {
            log.error("No user is present with email{} and mobileNo{}",workflowBean.getFormData().getEmail(),workflowBean.getFormData().getMobileNo());
            //Abort transaction
            //TBD
        }
        merchantInfo.setUsername(basicDetailsEntity.get(0).getEmail());
        String password=RandomStringUtils.random(8, "0123456789abcdefABCDEFXYZ");
        merchantInfo.setPassword(password);
        merchantInfo.setOldpassword(password);

        merchantInfo.setBasicDetailId(workflowBean.getBasicInfoBo().getId());
        merchantInfo.setOnboardingDataId(workflowBean.getOnBoardingDataBO().getId());
        merchantInfo.setCuisine(workflowBean.getFormData().getCuisine());
        merchantInfo.setMerchantType("cook");
        merchantInfo.setFoodCategory(workflowBean.getFormData().getFoodCategory());
        merchantInfo.setAddressId(workflowBean.getAddressBO().getId());
        merchantInfo.setBasicDetailId(workflowBean.getBasicInfoBo().getId());
        merchantInfo.setOnboardingDataId(workflowBean.getOnBoardingDataBO().getId());

        return merchantInfo;
    }
}
