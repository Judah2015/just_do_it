package indi.lzd.just_do_it.dao;

import indi.lzd.just_do_it.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByName(String name);
}
