package indi.lzd.just_do_it.dao;

import indi.lzd.just_do_it.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByName(String name);

    List<User> findByNameAndPassword(String name, String password);

    @Transactional
    @Modifying
    @Query(value = "delete from user where unix_timestamp(last_login_time) < unix_timestamp() - 300", nativeQuery = true)
    public int deleteLongTimeNoLoginUser();
}
