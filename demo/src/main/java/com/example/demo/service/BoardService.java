package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import java.util.List;

public interface BoardService {
    List<BoardDTO> getAllBoards();

    // 상세조회
    BoardDTO getBoardById(int id);

    // 글쓰기
    void insertBoard(BoardDTO board);

}