package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    int id;
    String title;
    String content;
    String writer;
    String created_at;
}

/*
 * CREATE TABLE `board` (
 `id` int NOT NULL AUTO_INCREMENT,
 `title` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
 `content` text COLLATE utf8mb4_general_ci NOT NULL,
 `writer` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
 `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
 `numofcomment` int DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
 */