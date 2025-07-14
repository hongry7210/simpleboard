package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDTO> getCommentsByBoardId(int boardId) {
        return commentMapper.selectCommentsByBoardId(boardId);
    }

    @Override
    public void insertComment(CommentDTO comment) {
        commentMapper.insertComment(comment);
    }
}
