package com.example.demo.mapper;

import com.example.demo.dto.FriendDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddFriendMapper {
    @Select("SELECT * FROM friend WHERE sender=#{sender} AND receiver=#{receiver}")
    FriendDTO findFriendRequest(String sender, String receiver);

    @Insert("INSERT INTO friend (sender, receiver, senderid, receiverid) VALUES (#{sender}, #{receiver}, #{senderid}, #{receiverid})")
    void insertFriendRequest(String sender, String receiver, String senderid, String receiverid);

    @Delete("Delete From friend Where sender=#{sender} AND receiver=#{receiver}")
    void deleteFriendRequest(String sender, String receiver);

    @Update("UPDATE friend SET receiver_accept = #{accept} WHERE sender = #{sender} AND receiver = #{receiver}")
    void updateFriendAccept(@Param("sender") String sender, @Param("receiver") String receiver, @Param("accept") int accept);

    @Select("SELECT sender FROM friend WHERE receiverid=#{receiver} AND receiver_accept=0")
    List<String> findRequestUser(String receiver);

    @Select("SELECT receiver FROM friend WHERE senderid=#{sender} AND receiver_accept=0")
    List<String> findSendUser(String sender);
}
