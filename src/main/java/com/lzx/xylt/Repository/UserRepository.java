package com.lzx.xylt.Repository;

import com.lzx.xylt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 87248 on 2020-05-01 11:26
 */
@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    //根据邮箱查找user
    User findUserByUserEmailAndUserPassword(String userEmail,String usrPassword);
    //查找userEmail
    User findByUserEmail(String userEmail);

}
