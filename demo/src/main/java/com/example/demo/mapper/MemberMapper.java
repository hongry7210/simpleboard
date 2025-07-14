package com.example.demo.mapper;

import com.example.demo.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    boolean save(MemberDTO member);
    MemberDTO findUserById(String userid);
}
