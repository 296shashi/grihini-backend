package com.project.grihini.panel.controller;


import com.project.grihini.panel.service.PanelLoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.grihini.panel.util.Constants.*;

@RestController
@Slf4j
public class PanelLoginController {

    @Autowired
    private PanelLoginServiceImpl panelLoginService;

    @PostMapping(value = LOGIN_ADMIN, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity loginAdmin(@RequestBody String username, String password,String token) {
       return  panelLoginService.loginAdmin(username,password,token);
    }

    @PostMapping(value = LOGIN_MERCHANT, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity loginMerchant(@RequestBody String username, String password) {
        return  panelLoginService.loginMerchant(username,password);
    }

    @PostMapping(value = CHANGE_MERCHANT_PASSWORD, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity changeMerchantPassword(@RequestBody String username, String currPassword, String newPassword) {
        if (panelLoginService.changePassword(username, currPassword, newPassword)) {
            return new ResponseEntity("Successfully updated password", HttpStatus.OK);
        }
        return new ResponseEntity("Failed to change password", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
