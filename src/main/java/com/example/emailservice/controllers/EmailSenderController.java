package com.example.emailservice.controllers;

import com.example.emailservice.DTO.EmailSendDTO;
import com.example.emailservice.application.EmailSenderService;
import com.example.emailservice.core.EmailModel;
import com.example.emailservice.core.exceptions.EmailServiceException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {
    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody @Valid EmailSendDTO emailSendDTO){
        try {
            EmailModel newEmail = new EmailModel(emailSendDTO);
            this.emailSenderService.sendEmail(newEmail);
            return ResponseEntity.ok("Email sent Sucefully");
        }catch (EmailServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while sending email");
        }
    }

}
