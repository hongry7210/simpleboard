package com.example.demo.service;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        MemberDTO user = memberMapper.findUserById(userid);
        if (user == null) {
            throw new UsernameNotFoundException("해당 사용자가 없습니다: " + userid);
        }
        // 커스텀 UserDetails로 반환!
        return new CustomUserDetails(
                user,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}