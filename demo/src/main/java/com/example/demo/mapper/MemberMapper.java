package com.example.demo.mapper;

import com.example.demo.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    boolean save(MemberDTO member);
    MemberDTO findUserById(String userid);
    void updatePassword(MemberDTO member);
    List<String> findUsernamesByPartial(@Param("query") String query);
    MemberDTO findUserByUsername(String username);
    List<String> findFriendUsernames(@Param("username") String username);
}
