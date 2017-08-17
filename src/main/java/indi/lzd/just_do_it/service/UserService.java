package indi.lzd.just_do_it.service;

import indi.lzd.just_do_it.dao.UserRepository;
import indi.lzd.just_do_it.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String registerUser(User user) {
        if (userRepository.findByName(user.getName()).isEmpty()) {
            userRepository.save(user);
            return "ok";
        }
        else {
            return "not ok";
        }
    }
}
