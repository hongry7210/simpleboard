package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommentDTO {
    private int id;
    private int board_id;
    private String writer;
    private String content;
    private String created_at;
    private int parentcomment_id;

    private List<CommentDTO> children = new ArrayList<>();
}

/*
 * CREATE TABLE `comment` (
 `id` int NOT NULL AUTO_INCREMENT,
 `board_id` int NOT NULL,
 `writer` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
 `content` text COLLATE utf8mb4_general_ci NOT NULL,
 `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
 `parentcomment_id` int DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `board_id` (`board_id`),
 CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`board_id`) REFERENCES `board` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
 */
