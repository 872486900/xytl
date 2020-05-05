package com.lzx.xylt.service;

import com.lzx.xylt.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by 87248 on 2020-05-01 11:54
 */
public interface UserService {
    //
    User userLogin(String userEmail,String userPassword);
    User userRegist(User user);
    User getUserEmail(String userEmail);
    //根据id查询user
    User getUser(Long userId);
    //修改user
    User updateUser(Long userId,User user);
    //获取所有用户
    Page<User> listUser(Pageable pageable);

    //删除用户
    void deleteUser(Long userId);
}
