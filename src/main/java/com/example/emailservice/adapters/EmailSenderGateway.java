package com.example.emailservice.adapters;

import com.example.emailservice.core.EmailModel;

public interface EmailSenderGateway {
    void sendEmail(EmailModel emailModel);
}
