package com.example.demo.mapper;

import com.example.demo.dto.ChatMessageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMessageMapper {
    int insertMessage(ChatMessageDTO message);
    List<ChatMessageDTO> selectMessagesBetweenUsers(
            @Param("user1") String user1,
            @Param("user2") String user2
    );
}
