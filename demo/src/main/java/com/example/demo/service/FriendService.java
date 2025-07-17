package com.example.demo.service;

import com.example.demo.dto.FriendDTO;
import com.example.demo.mapper.AddFriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    @Autowired
    AddFriendMapper am;

    public boolean addFriend(String sender, String receiver) {
        // 이미 친구거나 요청중인지 확인 후 없으면 insert
        FriendDTO existing = am.findFriendRequest(sender, receiver);
        if(existing != null) return false;
        am.insertFriendRequest(sender, receiver);
        return true;
    }

}
