package com.project.grihini.panel.service;

import com.project.grihini.panel.util.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public boolean sendSimpleMessage(Mail mail,String merchantId) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent());
        helper.setTo(mail.getTo());
        helper.setFrom(mail.getTo());

//        String onBoardingDoc = "Onboarding.pdf";
//        FileSystemResource file = new FileSystemResource(new File(onBoardingDoc));
//        helper.addAttachment("Invoice.pdf", file);
        try {
            emailSender.send(message);
            return true;
        }
        catch (Exception e)
        {
            log.error("Failed to send mail {}",e.getMessage());
        }
       return false;
    }

}