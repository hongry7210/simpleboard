package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendDTO {
    private Long id;
    private String sender;          // 친구 신청 보낸 username
    private String receiver;        // 친구 신청 받은 username
    private int senderCancel;       // 0: 정상, 1: 보낸 쪽에서 취소
    private int receiverAccept;     // 0: 대기/거절, 1: 수락
    private LocalDateTime createdAt;
}
