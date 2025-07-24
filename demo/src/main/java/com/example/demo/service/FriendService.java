package com.example.demo.service;

import com.example.demo.dto.FriendDTO;
import com.example.demo.mapper.AddFriendMapper;
import com.example.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    AddFriendMapper am;

    @Autowired
    MemberMapper mm;

    public boolean addFriend(String sender, String receiver) {
        // 이미 친구거나 요청중인지 확인 후 없으면 insert
        FriendDTO existing = am.findFriendRequest(sender, receiver);
        if(existing != null) return false;
        am.insertFriendRequest(sender, receiver);
        return true;
    }

    public void cancelFriend(String sender, String receiver){
        am.deleteFriendRequest(sender, receiver);
    }

    public int friendStatus (String sender, String receiver){
        FriendDTO connectStatus = am.findFriendRequest(sender, receiver);
        if(connectStatus == null) { //아직 보내지 않음(내가)
            FriendDTO connectStatusOpposit = am.findFriendRequest(receiver, sender);
            if(connectStatusOpposit == null) //DB에 아예 데이터가 없으면
                return 0; //친구 신청
            if(connectStatusOpposit.getReceiver_accept() == 0) //receiver가 보내고 sender가 안 받은 상태라면
                return 4;
            return 2; //receiver가 받은 상태라면 (친구)
        }
        if(connectStatus.getReceiver_accept() == 0) //상대가 안받음
            return 1;
        return 3; // receiver가 보낸 상태고 내가 안받은 상태라면 (친구요청 받기)
    }

    public boolean acceptFriendRequest(String sender, String receiver) {
        System.out.println(sender + receiver);

        // 1. 친구 요청이 실제로 존재하는지 확인
        FriendDTO request = am.findFriendRequest(sender, receiver);
        if(request == null) return false;

        // 2. 이미 수락된 상태인지 확인 (이미 친구면 true 반환)
        if(request.getReceiver_accept() == 1) return true;

        // 3. 친구 요청 수락 처리 (receiver_accept = 1로 업데이트)
        int result = am.updateFriendAccept(sender, receiver, 1);
        return true;
    }

    public List<String> getFriends(String username){
        return mm.findFriendUsernames(username);
    }
}
