package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import java.util.List;

public interface CommentService {
    List<CommentDTO> getCommentsByBoardId(int boardId);
    void insertComment(CommentDTO comment);
}
