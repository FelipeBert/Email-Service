package com.example.emailservice.application;

import com.example.emailservice.adapters.EmailSenderGateway;
import com.example.emailservice.core.EmailModel;
import com.example.emailservice.core.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSenderUseCase {
    private final EmailSenderGateway emailSenderGateway;

    @Autowired
    public EmailSenderService(EmailSenderGateway emailSenderGateway){
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(EmailModel emailModel) {
        this.emailSenderGateway.sendEmail(emailModel);
    }
}
