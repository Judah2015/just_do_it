package indi.lzd.just_do_it.service;

import indi.lzd.just_do_it.dao.UserRepository;
import indi.lzd.just_do_it.domain.User;
import indi.lzd.just_do_it.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String registerUser(User user) {
        if (userRepository.findByName(user.getName()).isEmpty()) {
            //再对密码做一次MD5
            user.setPassword(MD5Util.getMD5(user.getPassword()));
            userRepository.save(user);
            return "o";//意思是ok
        } else {
            return "n";//意思是name error
        }
    }

    public String loginUser(String name, Date lastLoginTime, String password) {
        List<User> user = userRepository.findByNameAndPassword(name, MD5Util.getMD5(password));
        if (user.isEmpty()) {
            return "n"; //意思是not ok
        } else {
            user.get(0).setLastLoginTime(lastLoginTime);
            userRepository.save(user);
            return "o"; //意思是ok
        }
    }
}
