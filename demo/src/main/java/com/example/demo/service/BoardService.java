package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import java.util.List;

public interface BoardService {
    // 글 목록 불러오기
    List<BoardDTO> getAllBoards();

    // 상세 조회
    BoardDTO getBoardById(int id);

    // 글 쓰기
    void insertBoard(BoardDTO board);

    // 글 찾기
    List<BoardDTO> getBoardsByWriter(String writer);

    // 글 삭제
    void deleteBoard(int id);

    // 댓글 삭제
    void deleteComment(int id);
}