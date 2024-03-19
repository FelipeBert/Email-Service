package com.example.emailservice.controllers;

import com.example.emailservice.DTO.EmailSendDTO;
import com.example.emailservice.application.EmailSenderService;
import com.example.emailservice.core.EmailModel;
import com.example.emailservice.core.exceptions.EmailServiceException;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    Logger logger = LogManager.getLogger(EmailSenderController.class);
    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody @Valid EmailSendDTO emailSendDTO){
        try {
            EmailModel newEmail = new EmailModel(emailSendDTO);
            logger.info("Sending an email to: {} from {} via HTTP request", emailSendDTO.getEmailTo(), emailSendDTO.getEmailFrom());
            this.emailSenderService.sendEmail(newEmail);
            logger.info("Email sent sucessfully to {} from {}", emailSendDTO.getEmailTo(), emailSendDTO.getEmailFrom());
            return ResponseEntity.ok("Email sent Sucefully");
        }catch (EmailServiceException e){
            logger.error("Error while sending an email via HTTP request!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while sending email");
        }
    }

    @GetMapping
    public ResponseEntity<Page<EmailModel>> getAllEmails(@PageableDefault(page = 0, size = 5,
                                                        sort = "id",
                                                        direction = Sort.Direction.DESC) Pageable pageable){
        logger.info("Fetching all emails");
        return new ResponseEntity<>(emailSenderService.findAll(pageable), HttpStatus.OK);
    }
}
