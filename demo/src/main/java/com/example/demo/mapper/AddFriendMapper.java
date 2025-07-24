package com.example.demo.mapper;

import com.example.demo.dto.FriendDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AddFriendMapper {
    @Select("SELECT * FROM friend WHERE sender=#{sender} AND receiver=#{receiver}")
    FriendDTO findFriendRequest(String sender, String receiver);

    @Insert("INSERT INTO friend (sender, receiver) VALUES (#{sender}, #{receiver})")
    void insertFriendRequest(String sender, String receiver);

    @Delete("Delete From friend Where sender=#{sender} AND receiver=#{receiver}")
    void deleteFriendRequest(String sender, String receiver);

    @Update("UPDATE friend SET receiver_accept = #{accept} WHERE sender = #{sender} AND receiver = #{receiver}")
    int updateFriendAccept(@Param("sender") String sender, @Param("receiver") String receiver, @Param("accept") int accept);
}
