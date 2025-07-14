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
