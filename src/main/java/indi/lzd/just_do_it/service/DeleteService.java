package indi.lzd.just_do_it.service;

import indi.lzd.just_do_it.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DeleteService {
    @Autowired
    UserRepository userRepository;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void delete() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//设置日期格式
        int affectedUsers = userRepository.deleteLongTimeNoLoginUser();
        System.out.println("于" + df.format(new Date()) + " " + "注销" + affectedUsers + "位5分钟内未登录的用户");
    }

}
