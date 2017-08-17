package indi.lzd.just_do_it.Controller;

import indi.lzd.just_do_it.domain.User;
import indi.lzd.just_do_it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("main")
public class MainController {
    @Autowired
    UserService userService;

    @RequestMapping("register")
    public String register(User user) {
        return userService.registerUser(user);
    }
}
