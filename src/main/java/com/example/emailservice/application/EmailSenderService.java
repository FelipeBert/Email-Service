package com.example.emailservice.application;

import com.example.emailservice.adapters.EmailSenderGateway;
import com.example.emailservice.core.EmailModel;
import com.example.emailservice.core.EmailSenderUseCase;
import com.example.emailservice.repository.EmailRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSenderUseCase {
    private final EmailSenderGateway emailSenderGateway;

    Logger logger = LogManager.getLogger(EmailSenderService.class);

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    public EmailSenderService(EmailSenderGateway emailSenderGateway){
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(EmailModel emailModel) {
        try{
            logger.info("Sending email to {} from {}", emailModel.getEmailTo(), emailModel.getEmailFrom());
            this.emailSenderGateway.sendEmail(emailModel);
            logger.info("Email sent sucessfully to {} from {}", emailModel.getEmailTo(), emailModel.getEmailFrom());
        }
        catch (Exception e){
            logger.error("Error sending email to {}", emailModel.getEmailTo());
        }
    }

    public Page<EmailModel> findAll(Pageable pageable) {
        logger.info("Fetching all Emails!");
        return  emailRepository.findAll(pageable);
    }
}
