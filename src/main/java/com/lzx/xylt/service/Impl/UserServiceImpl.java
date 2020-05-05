package com.lzx.xylt.service.Impl;

import com.lzx.xylt.NotFoundException;
import com.lzx.xylt.Repository.UserRepository;
import com.lzx.xylt.domain.User;
import com.lzx.xylt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 87248 on 2020-05-01 11:54
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User userLogin(String userEmail, String userPassword) {
        return userRepository.findUserByUserEmailAndUserPassword(userEmail,userPassword);
    }

    @Transactional
    @Override
    public User userRegist(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.getOne(userId);
    }

    @Transactional
    @Override
    public User updateUser(Long userId, User user) {
        User u = userRepository.getOne(userId);
        if (u==null){
            throw  new NotFoundException("不存在该类");
        }
        BeanUtils.copyProperties(user,u);
        return userRepository.save(u);
    }

    //获取用户信息
    @Override
    public Page<User> listUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    //删除用户
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);

    }
}
