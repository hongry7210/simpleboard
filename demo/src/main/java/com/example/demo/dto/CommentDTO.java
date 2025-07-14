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
