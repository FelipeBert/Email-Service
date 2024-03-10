package com.example.emailservice.infrastructure.sendgrid;

import com.example.emailservice.adapters.EmailSenderGateway;
import com.example.emailservice.core.EmailModel;
import com.example.emailservice.core.StatusEmail;
import com.example.emailservice.core.exceptions.EmailServiceException;
import com.example.emailservice.repository.EmailRepository;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.sendgrid.SendGrid;


import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class SendGridEmailSender implements EmailSenderGateway{
    private final SendGrid sendGrid;
    private final String fromEmail;

    @Autowired
    EmailRepository emailRepository;

    public SendGridEmailSender(@Autowired SendGrid sendGrid,
                               @Value("${twilio.sendgrid.from-email}") String fromEmail) {
        this.sendGrid = sendGrid;
        this.fromEmail = fromEmail;
    }

    @Override
    public void sendEmail(EmailModel emailModel) {
        emailModel.setLocalDateTime(LocalDateTime.now());
        Email from = new Email(this.fromEmail);
        Email toEmail = new Email(emailModel.getEmailTo());
        Content content = new Content("text/plain", emailModel.getText());

        Mail mail = new Mail(from, emailModel.getSubject(), toEmail, content);

        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);
            int statusCode = response.getStatusCode();

            if (statusCode < 200 || statusCode >= 300) {
                emailModel.setStatusEmail(StatusEmail.valueOf("ERROR"));
                throw new EmailServiceException(response.getBody());
            }
            emailModel.setStatusEmail(StatusEmail.valueOf("SENT"));
        } catch (IOException e) {
            e.printStackTrace();
            emailModel.setStatusEmail(StatusEmail.valueOf("ERROR"));
            throw new EmailServiceException(e.getMessage(), e.getCause());
        }
        finally {
            this.emailRepository.save(emailModel);
        }
    }
}