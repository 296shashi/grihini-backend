package com.project.grihini.panel.controller;

import com.project.grihini.panel.service.IMerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.project.grihini.panel.util.Constants.*;

@CrossOrigin
@RestController
@Slf4j
public class MerchantDetailController {

  @Autowired
  private IMerchantService merchantService;

  @GetMapping(value = FETCH_MERCHANT_DETAILS)
  public ResponseEntity getMerchantDetails(@RequestParam String merchantId) {
    return merchantService.fetchMerchantDetails(merchantId);
  }


  @GetMapping(value = FETCH_ALL_MERCHANT_DETAILS)
  public ResponseEntity getAllMerchantDetails() {
    return merchantService.fetchAllMerchantDetails();
  }


  @GetMapping(value = FETCH_MERCHANT_DETAILS_FROM_STATUS)
  public ResponseEntity getMerchantDetailsWithStatus(@RequestParam String status) {
    return merchantService.fetchMerchantDetailsOnStatus(status);
  }

  @PutMapping(value = APPROVE_MERCHANT, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public ResponseEntity updateMerchantDetailsPendingToApproved(@RequestParam Map<String,String> request) {
    return merchantService.handleMerchant(request.get("merchantId"),request.get("operation"));
  }

  @PutMapping(value = REJECT_MERCHANT, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public ResponseEntity updateMerchantDetailsPendingToReject(@RequestParam Map<String,String> request) {
    return merchantService.handleMerchant(request.get("merchantId"),request.get("operation"));
  }

}
