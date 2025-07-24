package com.example.demo.mapper;

import com.example.demo.dto.FriendDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddFriendMapper {
    @Select("SELECT * FROM friend WHERE sender=#{sender} AND receiver=#{receiver}")
    FriendDTO findFriendRequest(String sender, String receiver);

    @Insert("INSERT INTO friend (sender, receiver) VALUES (#{sender}, #{receiver})")
    void insertFriendRequest(String sender, String receiver);

    @Delete("Delete From friend Where sender=#{sender} AND receiver=#{receiver}")
    void deleteFriendRequest(String sender, String receiver);
}
