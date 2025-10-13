package com.example.demo.mapper;

import com.example.demo.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> selectAllBoards();

    // 상세 조회
    BoardDTO selectBoardById(int id);

    // 글쓰 기
    void insertBoard(BoardDTO board);

    // 글 찾기
    List<BoardDTO> findBoardsByWriter(String writer);

    // 글 삭제
    void deleteBoard(int id);

    // 댓글 삭제
    void deleteComment(int id);
}
