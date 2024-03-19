package com.example.emailservice.consumers;

import com.example.emailservice.DTO.EmailSendDTO;
import com.example.emailservice.application.EmailSenderService;
import com.example.emailservice.controllers.EmailSenderController;
import com.example.emailservice.core.EmailModel;
import com.example.emailservice.core.exceptions.EmailServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @Autowired
    EmailSenderService emailSenderService;
    Logger logger = LogManager.getLogger(EmailConsumer.class);
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailSendDTO emailSendDTO){
        try {
            EmailModel newEmail = new EmailModel(emailSendDTO);
            logger.info("Sending an email to: {} from {} via RabbitMQ", emailSendDTO.getEmailTo(), emailSendDTO.getEmailFrom());
            this.emailSenderService.sendEmail(newEmail);
            logger.info("Email sent sucessfully to {} from {}", emailSendDTO.getEmailTo(), emailSendDTO.getEmailFrom());
        }catch (EmailServiceException e){
            logger.error("Error while sending an email via RabbitMQ!");
        }
    }
}
