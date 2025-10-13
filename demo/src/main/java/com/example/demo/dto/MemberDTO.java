package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private int id;
    private String userid;
    private String userpass;
    private String username;
    private String useremail;
}

/*
 * CREATE TABLE `user` (
 `id` bigint NOT NULL AUTO_INCREMENT,
 `userid` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
 `userpass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
 `username` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
 `useremail` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
 */