package com.example.emailservice.repository;

import com.example.emailservice.core.EmailModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
