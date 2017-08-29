package indi.lzd.just_do_it.Controller;

import indi.lzd.just_do_it.domain.NoticeInfo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class WebSocketController {
    public static CopyOnWriteArraySet<String> nameOfOnlineUsers = new CopyOnWriteArraySet<String>();

    @MessageMapping("/notice")
    public NoticeInfo notice(NoticeInfo noticeInfo) {
        if (noticeInfo.getStatus().equals("on")) {
            nameOfOnlineUsers.add(noticeInfo.getName());
        } else {
            nameOfOnlineUsers.remove(noticeInfo.getName());
        }
        System.out.println(nameOfOnlineUsers);
        return noticeInfo;
    }

    @RequestMapping("/getNameOfOnlineUsers")
    @ResponseBody
    public CopyOnWriteArraySet<String> getNameOfOnlineUsers() {
        return nameOfOnlineUsers;
    }
}
