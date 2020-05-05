package com.lzx.xylt.service.Impl;

import com.lzx.xylt.Repository.AdminRepository;
import com.lzx.xylt.domain.Admin;
import com.lzx.xylt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 87248 on 2020-05-02 15:10
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin login(String adminName, String password) {
        Admin admin = adminRepository.findByAdminNameAndPassword(adminName, password);

        return admin;
    }
}
