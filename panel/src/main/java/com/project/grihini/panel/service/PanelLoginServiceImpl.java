package com.project.grihini.panel.service;

import com.project.grihini.core.oauth.entity.AdminEntity;
import com.project.grihini.core.oauth.repo.AdminRepo;
import com.project.grihini.core.onboarding.entity.MerchantInfoEntity;
import com.project.grihini.core.onboarding.repo.MerchantInfoRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PanelLoginServiceImpl {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private MerchantInfoRepo merchantInfoRepo;

    public ResponseEntity loginAdmin(String username,String password,String token)
    {
        Optional<AdminEntity> adminEntity1 = adminRepo.findById(Long.parseLong("1"));
       // List<AdminEntity>adminEntity=adminRepo.findAdminEntityByUsernameAndPassword(username,password);
       // if(CollectionUtils.isEmpty(adminEntity))
        if(null==adminEntity1)
        {
            log.error("Admin credentials username {} and password {} are not valid",username,password);
            return new ResponseEntity("Admin Login Failed,Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }
        //name,token,mob
        log.info("Admin logged in successfully");
        return new ResponseEntity("Admin logged in Successfully",HttpStatus.OK);
    }

    public ResponseEntity loginMerchant(String username,String password)
    {
        Optional<MerchantInfoEntity> merchantInfoEntity = merchantInfoRepo.findByUsernameAndPassword(username,password);
        if(!merchantInfoEntity.isPresent())
        {
            log.error("No Merchant found for username {} and password {}",username,password);
            return new ResponseEntity("Invalid username or password",HttpStatus.UNAUTHORIZED);
        }
        if(null==merchantInfoEntity.get().getOldpassword())
        {
            //TBD first time login ,redirect to change password
            //changePassword(username,password);
        }
        //TBD
        log.info("Merchant logged in successfully");
        return new ResponseEntity("Merchant logged in successfully",HttpStatus.OK);

    }

    public boolean changePassword(String username,String currPassword,String newPassword)
    {
        Optional<MerchantInfoEntity> merchantInfoEntity=merchantInfoRepo.findByUsernameAndPassword(username,currPassword);
        if(!merchantInfoEntity.isPresent())
        {
            log.error("No Merchant found for username {} and password {}",username,currPassword);
            return false;
        }
        if(merchantInfoEntity.get().getPassword().equals(currPassword))
        {
            merchantInfoEntity.get().setOldpassword(currPassword);
            merchantInfoEntity.get().setPassword(newPassword);
            try
            {
                merchantInfoRepo.save(merchantInfoEntity.get());
                return true;
            }
            catch (Exception e)
            {
                log.error("Failed to change password {}",e.getMessage());
            }
        }
        return false;
    }
}
