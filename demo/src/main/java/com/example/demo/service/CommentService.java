package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.mapper.CommentMapper;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getCommentsByBoardId(int boardId);
    void insertComment(CommentDTO comment);
    public List<CommentDTO> getCommentsByWriter(String writer);
}
