package com.example.emailservice.core;

import com.example.emailservice.DTO.EmailSendDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TABLE_EMAIL")
public class EmailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime localDateTime;
    @Enumerated(EnumType.STRING)
    private StatusEmail statusEmail;

    public EmailModel(EmailSendDTO emailSendDTO){
        this.ownerRef = emailSendDTO.getOwnerRef();
        this.emailFrom = emailSendDTO.getEmailFrom();
        this.emailTo = emailSendDTO.getEmailTo();
        this.subject = emailSendDTO.getSubject();
        this.text = emailSendDTO.getText();
    }
}
