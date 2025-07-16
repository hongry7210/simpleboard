package com.example.demo.controller;
import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;
    private final BoardService bs;
    private final CommentService cs;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("member", new MemberDTO());
        System.out.println("회원가입 창");
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("member") MemberDTO member){
        if(ms.save(member)==true)
            return "redirect:/login";
        else
            return "duplicateUser";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("member", new MemberDTO());
        System.out.println("로그인 창");
        return "login";
    }

    @GetMapping("/api/check-duplicate")
    @ResponseBody
    public boolean checkDuplicate(@RequestParam String userid) {
        return ms.findUserById(userid) != null;
    }

    @GetMapping("/user/modify")
    public String modifyUser(Model model, Principal principal) {
        String userid = principal.getName();
        MemberDTO user = ms.findUserById(userid);
        List<BoardDTO> myBoards = bs.getBoardsByWriter(user.getUsername());
        List<CommentDTO> myComments = cs.getCommentsByWriter(user.getUsername());

        model.addAttribute("user", user);
        model.addAttribute("myBoards", myBoards);
        model.addAttribute("myComments", myComments);
        return "modifyuser";
    }

    // 비밀번호 변경 처리
    @PostMapping("/user/checkpw-ajax")
    @ResponseBody
    public Map<String, String> checkPwAjax(@RequestBody Map<String, String> req) {
        String userid = req.get("userid");
        String oldpw = req.get("oldpw");
        MemberDTO user = ms.findUserById(userid);
        if (user != null && passwordEncoder.matches(oldpw, user.getUserpass())) {
            System.out.println("ok");
            return Map.of("result", "ok");
        } else {
            System.out.println("fail");
            return Map.of("result", "fail", "msg", "비밀번호가 일치하지 않습니다.");
        }
    }

    @PostMapping("/user/changepw-ajax")
    @ResponseBody
    public Map<String, String> changePwAjax(@RequestBody Map<String, String> req) {
        String userid = req.get("userid");
        String newpw = req.get("newpw");
        MemberDTO user = ms.findUserById(userid);
        if (user == null) return Map.of("result", "fail", "msg", "사용자 정보 없음");
        user.setUserpass(passwordEncoder.encode(newpw));
        ms.updatePassword(user);
        return Map.of("result", "ok");
    }

    @GetMapping("/finduser")
    public String finduserform(){
        return "finduser";
    }

    @GetMapping("/api/search-user")
    @ResponseBody
    public List<String> searchUsernames(@RequestParam String query) {
        return ms.findUsernamesByQuery(query); // 예시, 아래 참고
    }

    @GetMapping("/user/{username}")
    public String viewUserPage(@PathVariable String username, Model model) {
        MemberDTO user = ms.findUserByUsername(username);
        if (user == null) {
            return "usernotfound";
        }
        model.addAttribute("user", user);
        // ... 추가 정보 (게시글 등)
        return "viewuser";
    }
}
