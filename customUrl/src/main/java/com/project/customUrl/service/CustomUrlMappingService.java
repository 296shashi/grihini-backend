package com.project.customUrl.service;

import com.project.customUrl.entity.CustomUrlEntity;
import com.project.customUrl.repository.CustomUrlEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomUrlMappingService {

    @Autowired
    private CustomUrlEntityRepo customUrlEntityRepo;

    public ResponseEntity getShortUrl(String url)
    {
        UUID uuid = UUID.randomUUID();
        CustomUrlEntity customUrlEntity = new CustomUrlEntity();
        customUrlEntity.setLongUrl(url);
        customUrlEntity.setShortUrl(uuid.toString());
        customUrlEntity.setAccessCount(customUrlEntity.getAccessCount()+1);
        Calendar c= Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        Date expiryDate=c.getTime();
        customUrlEntity.setExpirationDate(expiryDate);
        customUrlEntityRepo.save(customUrlEntity);

        return new ResponseEntity("shortUrl is " + uuid, HttpStatus.OK);
    }

    public ResponseEntity getActualUrl(String url)
    {
        //Optional<CustomUrlEntity> optional = customUrlEntityRepo.findByShortUrl(url);
        CustomUrlEntity customUrlEntity = customUrlEntityRepo.findByShortUrl(url);
        String actualUrl = customUrlEntity.getLongUrl();
        return new ResponseEntity("ActualUrl is " + actualUrl, HttpStatus.OK);
    }
}
