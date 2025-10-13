package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mailserver7210@gmail.com"); // application.properties의 username과 동일하게 설정
        message.setTo(to); // 수신자
        message.setSubject(subject); // 제목
        message.setText(text); // 내용
        emailSender.send(message);
    }
}
