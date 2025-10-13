package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.VerificationDTO;

@Mapper
public interface VerificationMapper {
    @Insert("INSERT INTO verification (userid, verifyCode, expireAt) " +
            "VALUES (#{userid}, #{verifyCode}, NOW() + INTERVAL 30 MINUTE) " +
            "ON DUPLICATE KEY UPDATE " +
            "verifyCode = VALUES(verifyCode), " +
            "expireAt = VALUES(expireAt)")
    void saveOrUpdate(VerificationDTO verificationInfo);

    @Select("SELECT * FROM verification WHERE userid = #{userid}")
    VerificationDTO findCodeByUserId(String userid);

    @Delete("DELETE FROM verification WHERE userid = #{userid}")
    void deleteCodeByUserId(String userid);
}
