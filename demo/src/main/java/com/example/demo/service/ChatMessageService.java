package com.example.demo.service;

import com.example.demo.dto.ChatMessageDTO;
import com.example.demo.mapper.ChatMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public int save(ChatMessageDTO message) {
        // 시간 세팅 (필요시)
        if (message.getSendTime() == null) {
            message.setSendTime(LocalDateTime.now());
        }
        return chatMessageMapper.insertMessage(message);
    }

    public List<ChatMessageDTO> getChatHistory(String user1, String user2) {
        return chatMessageMapper.selectMessagesBetweenUsers(user1, user2);
    }

    public void sendChatMessage(ChatMessageDTO message) {
        // 1. DB 저장 (이 부분은 실제 저장 코드로 대체)
        chatMessageMapper.insertMessage(message);

        // 2. 본인에게 메시지 전송 (옵션)
        messagingTemplate.convertAndSendToUser(
                message.getSender(),
                "/queue/messages",
                message
        );

        // 3. 상대방에게 메시지 전송
        messagingTemplate.convertAndSendToUser(
                message.getReceiver(),
                "/queue/messages",
                message
        );
    }

    public void sendAndSaveMessage(ChatMessageDTO message) {
        // 1. 메시지 DB 저장 (생략)
        chatMessageMapper.insertMessage(message);

        // 2. 본인(보낸사람)에게 메시지 전송
        messagingTemplate.convertAndSendToUser(
                message.getSender(), "/queue/messages", message
        );
        System.out.println(message.getSender());
        // 3. 상대방(받는사람)에게도 메시지 전송 (여기가 중요!)
        if (!message.getSender().equals(message.getReceiver())) { // 자기 자신이 아닐 때만
            System.out.println(message.getReceiver());
            messagingTemplate.convertAndSendToUser(
                    message.getReceiver(), "/queue/messages", message
            );
        }
    }

}
