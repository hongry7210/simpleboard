package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.FriendInfoDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;
import com.example.demo.service.FriendService;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private FriendService friendService;

    @GetMapping("/boardmain")
    public String boardList(Model model, Principal principal) {
        List<BoardDTO> boards = boardService.getAllBoards();
        List<String> requestusers = friendService.getRequestlist(principal.getName());
        List<String> sendusers = friendService.getSendlist(principal.getName());
        String myName = memberService.findUserById(principal.getName()).getUsername();

        model.addAttribute("myUsername", myName);
        model.addAttribute("sendusers", sendusers);
        model.addAttribute("requestusers", requestusers);
        model.addAttribute("boards", boards);
        return "boardmain";
    }

    @GetMapping("/board/view")
    public String boardView(@RequestParam("id") int id, Model model, Principal principal) {
        // 게시글 정보
        BoardDTO board = boardService.getBoardById(id);

        // 모든 댓글 리스트
        List<CommentDTO> allComments = commentService.getCommentsByBoardId(id);

        // 1. parent(부모 댓글)만 추출
        List<CommentDTO> parentComments = allComments.stream()
                .filter(c -> c.getParentcomment_id() == 0)
                .collect(Collectors.toList());

        // 2. 각 parent에 자식 댓글 붙이기
        for (CommentDTO parent : parentComments) {
            List<CommentDTO> children = allComments.stream()
                    .filter(c -> c.getParentcomment_id() == parent.getId())
                    .collect(Collectors.toList());
            parent.setChildren(children); // children 필드 setter 사용
        }

        // 3. Model에 데이터 담기
        model.addAttribute("parentComments", parentComments);
        model.addAttribute("board", board);
        model.addAttribute("username", memberService.findUserById(principal.getName()).getUsername());

        // 로그인한 사용자명(없으면 null)
        if (principal != null) {
            String userid = principal.getName();
            MemberDTO member = memberService.findUserById(userid);
            String username = member != null ? member.getUsername() : null;
            model.addAttribute("username", username);
        }

        return "boardview";
    }



    @GetMapping("/board/write")
    public String writeForm() {
        return "boardwrite"; // templates/boardwrite.html
    }

    @PostMapping("/board/write")
    public String writeProc(BoardDTO board) {
        boardService.insertBoard(board);
        return "redirect:/boardmain";
    }

    @PostMapping("/board/delete/{id}")
    public String deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
        return "redirect:/boardmain";
    }

}
