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
    private String senderid;
    private String receiverid;
    private int receiver_accept;     // 0: 대기/거절, 1: 수락
    private LocalDateTime createdAt;
}

/*
 * CREATE TABLE `friend` (
 `id` bigint NOT NULL AUTO_INCREMENT,
 `sender` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
 `receiver` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
 `receiver_accept` tinyint(1) DEFAULT '0',
 `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
 `senderid` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
 `receiverid` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `unique_friend` (`sender`,`receiver`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
 */