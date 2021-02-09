package com.project.grihini.panel.service;

import com.google.gson.internal.$Gson$Preconditions;
import com.project.grihini.core.onboarding.bo.MerchantInfoBO;
import com.project.grihini.core.onboarding.entity.*;
import com.project.grihini.core.onboarding.enums.OnboardingStatus;
import com.project.grihini.core.onboarding.mapper.AddressMapper;
import com.project.grihini.core.onboarding.mapper.BasicDetailMapper;
import com.project.grihini.core.onboarding.mapper.MerchantInfoMapper;
import com.project.grihini.core.onboarding.model.enums.AddressStatus;
import com.project.grihini.core.onboarding.model.enums.PanelUserStatus;
import com.project.grihini.core.onboarding.repo.*;
import com.project.grihini.core.panel.model.PanelUser;
import com.project.grihini.panel.dto.MerchantDetailsDTO;
import com.project.grihini.panel.util.Mail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class MerchantServiceImpl implements IMerchantService {

  @Autowired
  private AddressRepo addressRepo;

  @Autowired
  private MerchantInfoRepo merchantInfoRepo;

  @Autowired
  private BasicInfoRepo basicInfoRepo;

  @Autowired
  private MenuInfoRepo menuInfoRepo;

  @Autowired
  private OnBoardingDataRepo onBoardingDataRepo;

  @Autowired
  private EmailService emailService;

  @Override
  public ResponseEntity fetchMerchantDetails(String merchantId) {

    Optional<MerchantInfoEntity> merchantInfoEntity = merchantInfoRepo.findByMerchantId(merchantId);
    if(!merchantInfoEntity.isPresent())
    {
      log.error("No Merchant found with id {}",merchantId);
      return new ResponseEntity("No Merchant found",HttpStatus.NO_CONTENT);
    }
    Optional<AddressEntity> addressEntity = addressRepo.findById(merchantInfoEntity.get().getAddressId());
    Optional<BasicDetailsEntity> basicDetailsEntity = basicInfoRepo.findById(merchantInfoEntity.get().getBasicDetailId());
//    MenuInfoEntity menuInfoEntity = menuInfoRepo.findByMerchant_info_id(Long.parseLong(merchantId));
    MerchantDetailsDTO merchantDetailsDTO = new MerchantDetailsDTO();
    merchantDetailsDTO.setAddressBO(AddressMapper.INSTANCE.entityToBo(addressEntity.get()));
    merchantDetailsDTO.setBasicInfoBo(BasicDetailMapper.INSTANCE.entityToBo(basicDetailsEntity.get()));
    merchantDetailsDTO.setMerchantInfoBO(MerchantInfoMapper.INSTANCE.entityToBo(merchantInfoEntity.get()));
    //Will add menu details as well once admin panel attached and menu details are added by him

    return new ResponseEntity(merchantDetailsDTO, HttpStatus.OK);

  }

  @Override
  public ResponseEntity  fetchMerchantDetailsOnStatus(String status)
  {
    List<OnboardingStatus> enumValues = new ArrayList<>();
    if("ALL".equalsIgnoreCase(status)){
      enumValues.addAll(EnumSet.allOf(OnboardingStatus.class));
    } else {
      enumValues.add(EnumUtils.getEnum(OnboardingStatus.class, status));
    }
    if(null==enumValues.get(0))//TBD
    {
      log.error("Invalid query,No status matches");
      return new ResponseEntity("No status matches",HttpStatus.NO_CONTENT);
    }
    List<Long> onBoardingDataEntities = onBoardingDataRepo.findIdByStatus(enumValues);
    List<MerchantInfoEntity> merchantInfoEntityList = merchantInfoRepo.findAllByBasicDetailIdIsIn(onBoardingDataEntities);

    JSONObject jsonObject=new JSONObject();
    Map<String, JSONObject> response=new HashMap<>();
    ArrayList list = new ArrayList();
    for(int i=0;i<merchantInfoEntityList.size();i++)
    {
      JSONObject jSONObject = new JSONObject();
      Long OnboardingDataId=merchantInfoEntityList.get(i).getOnboardingDataId();
      Long basicDetailId=merchantInfoEntityList.get(i).getBasicDetailId();
      jSONObject.put("Onboarding status",onBoardingDataRepo.findStatusById(OnboardingDataId));
      jSONObject.put("Merchant status", basicInfoRepo.findById(basicDetailId).get().getStatus());
      response.put(merchantInfoEntityList.get(i).getMerchantId(),jSONObject);
    }
    list.add(response);
    jsonObject.put("MerchantList",list);
    MerchantInfoMapper.INSTANCE.entityListToBo(merchantInfoEntityList);
    return new ResponseEntity(jsonObject.toString(),HttpStatus.OK);
  }


  private boolean sendApprovalMail(String merchantId,BasicDetailsEntity basicDetailsEntity)
  {
    Mail mail=new Mail();
    mail.setFrom("service.grihini@gmail.com");
    mail.setTo(basicDetailsEntity.getEmail());
    mail.setSubject("Welcome to Grihini.com");
    Optional<MerchantInfoEntity> merchantInfoEntity=merchantInfoRepo.findByMerchantId(merchantId);
    StringBuilder builder=new StringBuilder();
    builder.append("Thanks for joining Grihini,Have a nice day!\n");
    builder.append("username - "+merchantInfoEntity.get().getUsername()+"\n");
    builder.append("password - "+merchantInfoEntity.get().getPassword()+"\n");
    builder.append("Kindly change you password");

    mail.setContent(builder.toString());
    try {
      return emailService.sendSimpleMessage(mail,merchantId);
    }
    catch (Exception e)
    {
      log.error("Something went wrong");
    }
    return false;
  }

  private ResponseEntity approveMerchant(String merchantId,OnBoardingDataEntity onBoardingDataEntity,BasicDetailsEntity basicDetailsEntity,Long addressId)
  {
    Optional<AddressEntity> optionalAddressEntity=addressRepo.findById(addressId);
    if(!optionalAddressEntity.isPresent())
    {
      log.error("Address details is not available for this merchant {}",merchantId);
      return new ResponseEntity("Merchant has no address details,something went wrong",HttpStatus.NO_CONTENT);
    }
    AddressEntity addressEntity=optionalAddressEntity.get();
    if(basicDetailsEntity.getStatus().name().equals("INACTIVE"))
    {
      basicDetailsEntity.setStatus(PanelUserStatus.ACTIVE);
      addressEntity.setStatus(AddressStatus.ACTIVE);
      onBoardingDataEntity.setStatus(OnboardingStatus.APPROVED);
      basicInfoRepo.save(basicDetailsEntity);
      addressRepo.save(addressEntity);
      onBoardingDataRepo.save(onBoardingDataEntity);
      sendApprovalMail(merchantId,basicDetailsEntity);
      log.info("Merchant successfully Approved!!!");
      return new ResponseEntity("Merchant successfully Approved!!!",HttpStatus.OK);
    }
    else if(basicDetailsEntity.getStatus().name().equals("ACTIVE"))
    {
      log.info("Merchant already Approved!!!");
      return new ResponseEntity("Merchant already Approved!!!",HttpStatus.OK);
    }
    else
    {
      log.info("Something went wrong,Onboarding has not been completed for merchant{}",merchantId);
      return new ResponseEntity("Failed to approve Merchant",HttpStatus.NO_CONTENT);
    }
  }


  private ResponseEntity rejectMerchant(String merchantId,OnBoardingDataEntity onBoardingDataEntity,BasicDetailsEntity basicDetailsEntity,Long addressId)
  {
    Optional<AddressEntity> optionalAddressEntity=addressRepo.findById(addressId);
    if(!optionalAddressEntity.isPresent())
    {
      log.error("Address details is not available for this merchant {}",merchantId);
      return new ResponseEntity("Merchant has no address details,something went wrong",HttpStatus.NO_CONTENT);
    }
    AddressEntity addressEntity=optionalAddressEntity.get();
    if(basicDetailsEntity.getStatus().name().equals("ACTIVE"))
    {
      basicDetailsEntity.setStatus(PanelUserStatus.INACTIVE);
      addressEntity.setStatus(AddressStatus.INACTIVE);
      onBoardingDataEntity.setStatus(OnboardingStatus.REJECTED);
      basicInfoRepo.save(basicDetailsEntity);
      addressRepo.save(addressEntity);
      onBoardingDataRepo.save(onBoardingDataEntity);
      log.info("Merchant {} successfully rejected!!!",merchantId);
      return new ResponseEntity("Merchant successfully Rejected!",HttpStatus.OK);
    }
    else if(basicDetailsEntity.getStatus().name().equals("INACTIVE"))
    {
      log.info("Merchant already rejected!!!");
      return new ResponseEntity("Merchant already Rejected!!!",HttpStatus.OK);
    }
    else
    {
      log.info("Something went wrong,Onboarding has not been completed for merchant{}",merchantId);
      return new ResponseEntity("Failed to reject Merchant",HttpStatus.NO_CONTENT);
    }

  }

  @Override
  public ResponseEntity handleMerchant(String merchantId,String operation)
  {

    try
    {
      Optional<MerchantInfoEntity> merchantInfoEntity = merchantInfoRepo.findByMerchantId(merchantId);
      if(!merchantInfoEntity.isPresent())
      {
        log.error("No Merchant found with id {}",merchantId);
        return new ResponseEntity("No Merchant found",HttpStatus.NO_CONTENT);
      }
      Optional<BasicDetailsEntity> basicDetailsEntity= null;
      Optional<AddressEntity> addressEntity=null;
       try {
        Long onBoardingId = merchantInfoEntity.get().getOnboardingDataId();
        Optional<OnBoardingDataEntity> onBoardingDataEntity = onBoardingDataRepo.findById(onBoardingId);
        if(!onBoardingDataEntity.isPresent())
        {
          log.error("No OnBoarding Data is available for this merchant {}",merchantId);
          return new ResponseEntity("Something went wrong,Not a valid merchant",HttpStatus.NO_CONTENT);
        }
        Long basicDetailId=merchantInfoEntity.get().getBasicDetailId();
        Long addressId=merchantInfoEntity.get().getAddressId();
         if(onBoardingDataEntity.get().getStatus().equals("INIT"))
        {
          log.error("Insufficient data for this merchant,still in init state");
          return new ResponseEntity("Merchant has not completed onboarding,something went wrong",HttpStatus.NO_CONTENT);
        }
         basicDetailsEntity = basicInfoRepo.findById(basicDetailId);
        if(!basicDetailsEntity.isPresent())
        {
          log.error("Basic details is not available for this merchant {}",merchantId);
          return new ResponseEntity("Merchant has invalid datails,something went wrong",HttpStatus.NO_CONTENT);
        }

        switch (operation)
        {
          case "APPROVE":
            return approveMerchant(merchantId,onBoardingDataEntity.get(),basicDetailsEntity.get(),addressId);
          case "REJECT" :
            return rejectMerchant(merchantId,onBoardingDataEntity.get(),basicDetailsEntity.get(),addressId);
          default:
            log.error("Invalid operation");
        }
      } catch (Exception e) {
        log.error("Failed to process this request,Insufficient data for Merchant {}",e.getMessage());
      }
    }
    catch (Exception e)
    {
      log.error("ERROR occurred while handling this request for merchant with id {} with error {} ",merchantId,e.getMessage());
    }
    return new ResponseEntity("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ResponseEntity fetchAllMerchantDetails()
  {

    JSONObject object=new JSONObject();
    List<MerchantInfoEntity> merchantInfoEntityList=merchantInfoRepo.findAll();

    for(int i=0;i<merchantInfoEntityList.size();i++)
    {
      JSONObject jsonObject=new JSONObject();
      Long basicDetailId=merchantInfoEntityList.get(i).getBasicDetailId();
      jsonObject.put("id",merchantInfoEntityList.get(i).getMerchantId());
      jsonObject.put("name",basicInfoRepo.findFullNameById(basicDetailId));
      jsonObject.put("Dob",basicInfoRepo.findById(basicDetailId).get().getDob());
      jsonObject.put("address",addressRepo.findById(merchantInfoEntityList.get(i).getAddressId()).toString());
      jsonObject.put("status",basicInfoRepo.findStatusById(basicDetailId));
      jsonObject.put("description","This is testing description");
      object.put(merchantInfoEntityList.get(i).getMerchantId(),jsonObject);
    }
    return new ResponseEntity(object.toString(),HttpStatus.OK);
  }
}
