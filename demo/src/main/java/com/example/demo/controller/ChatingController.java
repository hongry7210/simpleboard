package com.example.demo.controller;

import com.example.demo.dto.ChatMessageDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.ChatMessageService;
import com.example.demo.service.FriendService;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class ChatingController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send") // /app/chat.send로 전송됨
    @SendToUser("/queue/messages") // 본인에게만, 브로드캐스트는 @SendTo("/topic/방이름")
    public void send(ChatMessageDTO message, Principal principal) {
        chatMessageService.sendAndSaveMessage(message);
    }

    @GetMapping("/chating")
    public String chatingPage(Model model, Principal principal) {
        // 현재 로그인된 사용자 이름
        String username = principal.getName();
        MemberDTO user = memberService.findUserById(username);
        // 해당 사용자의 친구 목록 조회 (이름만 리스트로)
        List<String> friends = friendService.getFriends(user.getUsername());

        model.addAttribute("friends", friends);
        model.addAttribute("me", username); // 본인 정보도 전달

        return "chating"; // chating.html
    }

    // 1:1 채팅방 (chatroom.html)
    @GetMapping("/chat/room")
    public String chatRoomPage(@RequestParam("friend") String friend, Model model, Principal principal) {
        String username = memberService.findUserById(   principal.getName()).getUsername();
        List<ChatMessageDTO> chatHistory = chatMessageService.getChatHistory(username, friend);
        model.addAttribute("me", username);
        model.addAttribute("friend", friend);
        model.addAttribute("chatHistory", chatHistory);
        return "chatroom";  // templates/chatroom.html
    }


}
