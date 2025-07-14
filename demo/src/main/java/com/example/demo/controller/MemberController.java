package com.example.demo.controller;
import com.example.demo.dto.BoardDTO;
import jakarta.servlet.http.HttpServletRequest;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;

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
}
