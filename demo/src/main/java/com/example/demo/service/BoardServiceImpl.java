package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getAllBoards() {
        return boardMapper.selectAllBoards();
    }

    @Override
    public BoardDTO getBoardById(int id) {
        return boardMapper.selectBoardById(id);
    }

    @Override
    public void insertBoard(BoardDTO board) {
        boardMapper.insertBoard(board);
    }

    @Override
    public List<BoardDTO> getBoardsByWriter(String writer) {
        return boardMapper.findBoardsByWriter(writer);
    }

    @Override
    public void deleteBoard(int id) {
        boardMapper.deleteBoard(id);
    }

    @Override
    public void deleteComment(int id) {
        boardMapper.deleteComment(id);
    }
}
