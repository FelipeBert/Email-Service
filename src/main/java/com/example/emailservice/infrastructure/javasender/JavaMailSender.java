package com.example.emailservice.infrastructure.javasender;

import com.example.emailservice.adapters.EmailSenderGateway;
import com.example.emailservice.core.EmailModel;
import com.example.emailservice.core.StatusEmail;
import com.example.emailservice.repository.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JavaMailSender implements EmailSenderGateway {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private org.springframework.mail.javamail.JavaMailSender emailSender;

    public JavaMailSender(org.springframework.mail.javamail.JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Transactional
    @Override
    public void sendEmail(EmailModel emailModel) {
        emailModel.setLocalDateTime(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
             this.emailRepository.save(emailModel);
        }
    }
}
