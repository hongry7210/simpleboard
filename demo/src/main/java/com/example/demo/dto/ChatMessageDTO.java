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
