package com.example.emailservice.repository;

import com.example.emailservice.core.EmailModel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
