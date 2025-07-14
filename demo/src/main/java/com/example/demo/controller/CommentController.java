package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 게시글 상세보기에서 댓글 목록을 함께 조회한다고 가정
    // 댓글만 단독 조회가 필요하면 아래 GET Mapping 활용
    @GetMapping("/comment/list")
    @ResponseBody
    public List<CommentDTO> commentList(@RequestParam("board_id") int boardId) {
        return commentService.getCommentsByBoardId(boardId);
    }

    // 댓글 등록
    @PostMapping("/comment/write")
    public String writeComment(CommentDTO comment) {
        // parentcomment_id가 없으면 0으로 처리(최상위 댓글)
        if (comment.getParentcomment_id() == 0) {
            comment.setParentcomment_id(0);
        }
        commentService.insertComment(comment);
        return "redirect:/board/view?id=" + comment.getBoard_id();
    }

}
