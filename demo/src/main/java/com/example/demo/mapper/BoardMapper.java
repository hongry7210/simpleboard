package com.example.demo.mapper;

import com.example.demo.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> selectAllBoards();

    // 상세조회
    BoardDTO selectBoardById(int id);

    // 글쓰기
    void insertBoard(BoardDTO board);
}
