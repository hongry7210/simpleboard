package com.example.demo.controller;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VerificationDTO;
import com.example.demo.mapper.VerificationMapper;
import com.example.demo.service.EmailService;

@RestController
public class MailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationMapper verificationMapper;

    @PostMapping("/send-mail")
    public String sendMail(@RequestParam("email") String email, @RequestParam("userid") String userid) {

        SecureRandom rand = new SecureRandom();
        int randomnumber = rand.nextInt(900000)+100000;
        String code = String.valueOf(randomnumber);
        System.out.println(email);
        emailService.sendSimpleMessage(
            email, // 👈 파라미터로 받은 이메일
            "회원가입 인증 메일입니다.",
            "인증번호는 [" + code + "]입니다.\n30분 안에 등록을 완료해 주세요." 
        );

        VerificationDTO v = new VerificationDTO();
        v.setUserid(userid);
        v.setVerifyCode(code);

        verificationMapper.saveOrUpdate(v);

        return "전송 완료";
    }

    @PostMapping("/api/verify-code")
    public boolean verifyCode(@RequestParam("userid") String userid, @RequestParam("code") String code) {
        
        // 1. DB에서 해당 유저의 인증 정보 조회
        VerificationDTO storedInfo = verificationMapper.findCodeByUserId(userid);

        if (storedInfo == null) {
            return false; // 저장된 정보 없음
        }
        
        // 2. 코드 일치 및 만료 시간 확인
        boolean isCodeMatch = storedInfo.getVerifyCode().equals(code);
        boolean isNotExpired = storedInfo.getExpireAt().isAfter(LocalDateTime.now());
        
        if (isCodeMatch && isNotExpired) {
            // 3. 인증 성공 시, DB에서 해당 정보 삭제 (재사용 방지)
            verificationMapper.deleteCodeByUserId(userid);
            return true;
        }

        return false; // 인증 실패
    }
}
