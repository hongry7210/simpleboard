package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper mm;
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean save(MemberDTO member){
        if(mm.findUserById(member.getUserid()) != null) {
            return false;
        }
        member.setUserpass(encoder.encode(member.getUserpass()));
        mm.save(member);
        return true;
    }

    public MemberDTO findUserById(String userid){
        return mm.findUserById(userid);
    }

    public void updatePassword(MemberDTO member) {
        mm.updatePassword(member); // member에는 userid, userpass 세팅
    }

}
