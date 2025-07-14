package com.example.demo.mapper;

import com.example.demo.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentDTO> selectCommentsByBoardId(int boardId);
    void insertComment(CommentDTO comment);
}
