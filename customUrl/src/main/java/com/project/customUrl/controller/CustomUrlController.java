package com.project.customUrl.controller;

import com.project.customUrl.service.CustomUrlMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.project.customUrl.utils.Constants.*;

@CrossOrigin
@RestController
@Slf4j
public class CustomUrlController {

    @Autowired
    private CustomUrlMappingService customUrlMappingService;

    @GetMapping(value = GET_SHORT_URL)
    public ResponseEntity getShortUrl(@RequestParam String actualUrl) {
        return customUrlMappingService.getShortUrl(actualUrl);
    }

    @GetMapping(value = GET_ACTUAL_URL)
    public ResponseEntity getActualUrl(@RequestParam String shortUrl) {
        return customUrlMappingService.getActualUrl(shortUrl);
    }
}
