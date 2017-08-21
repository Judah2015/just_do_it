package indi.lzd.just_do_it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DeleteOldService {
    @Autowired
    UserService userService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void delete() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//设置日期格式
        userService.deleteOld();
        System.out.println(df.format(new Date()) + " " + "？");
    }

}
