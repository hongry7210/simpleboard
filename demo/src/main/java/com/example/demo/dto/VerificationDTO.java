package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationDTO {
    private int id;
    private String userid;
    private String verifyCode;
    private LocalDateTime createdAt;
    private LocalDateTime expireAt;
}

/*
 * CREATE TABLE verification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,         
    userid varchar(50) NOT NULL UNIQUE,            
    verifyCode VARCHAR(10),                           
    expireAt DATETIME,                            
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
 */