package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.FriendService;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AddFreindController {

    @Autowired
    MemberService ms;

    @Autowired
    FriendService fs;

    @GetMapping("/userinfo/{username}")
    public String viewUserPage(@PathVariable String username, Model model, Principal principal) {
        MemberDTO user = ms.findUserByUsername(username);
        String myName = ms.findUserById(principal.getName()).getUsername();
        boolean isRequested = fs.isFriendRequestPending(myName, user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("isRequested", isRequested);
        model.addAttribute("myUsername", myName);
        return "userinfo";
    }


    @PostMapping("/api/add-friend")
    @ResponseBody
    public Map<String, String> addFriend(@RequestBody Map<String, String> req, Principal principal) {
        String sender = (ms.findUserById(principal.getName())).getUsername();
        String receiver = req.get("receiver");
        System.out.println(receiver);
        if(sender.equals(receiver)) {
            return Map.of("result", "fail", "msg", "자기 자신에게는 친구 신청할 수 없습니다.");
        }
        boolean ok = fs.addFriend(sender, receiver);
        if(ok) return Map.of("result", "ok");
        else return Map.of("result", "fail", "msg", "이미 신청한 친구입니다");
    }

    @PostMapping("/api/cancel-friend")
    @ResponseBody
    public Map<String, String> cancelFriend(@RequestBody Map<String, String> req, Principal principal){
        String sender = (ms.findUserById(principal.getName())).getUsername();
        String receiver = req.get("receiver");
        System.out.println(sender + " 님이 " + receiver + " 님에게 보낸 친구 요청을 취소합니다");
        fs.cancelFriend(sender, receiver);
        return Map.of("result", "ok");
    }

}
