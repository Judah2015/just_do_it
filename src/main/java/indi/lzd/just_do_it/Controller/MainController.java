package indi.lzd.just_do_it.Controller;

import com.google.code.kaptcha.Constants;
import indi.lzd.just_do_it.domain.User;
import indi.lzd.just_do_it.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("main")
public class MainController {
    @Autowired
    UserService userService;

    @RequestMapping("register")
    public String register(HttpServletRequest request, User user, String captchaUser) {
        HttpSession session = request.getSession();
        String captchaServer = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!captchaUser.equals(captchaServer)) {
            return "c";//意思是captcha error
        } else {
            return userService.registerUser(user);
        }
    }

    @RequestMapping("login")
    public String login(String name, Date lastLoginTime, String password) {
        return userService.loginUser(name, lastLoginTime, password);
    }
}
