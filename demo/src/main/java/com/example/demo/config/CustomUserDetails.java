package com.example.demo.config;

import com.example.demo.dto.MemberDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private String userid;
    private String username; // 화면에 보여줄 이름
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(MemberDTO user, Collection<? extends GrantedAuthority> authorities) {
        this.userid = user.getUserid();
        this.username = user.getUsername(); // MemberDTO에서 가져옴
        this.password = user.getUserpass();
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return userid; // 로그인에 사용할 아이디
    }
    public String getDisplayName() {
        return username; // 화면 표시용
    }
    @Override
    public String getPassword() { return password; }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

}
