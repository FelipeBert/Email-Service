package com.example.emailservice.core;

import com.example.emailservice.DTO.EmailSendDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TABLE_EMAIL")
public class EmailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
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
