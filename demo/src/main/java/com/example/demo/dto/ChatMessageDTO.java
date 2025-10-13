package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDTO {
    private int id;
    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime sendTime;
}

/*
 * CREATE TABLE `chatmassage` (
 `id` bigint NOT NULL AUTO_INCREMENT,
 `sender` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
 `receiver` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
 `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
 `time` datetime DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
 */
