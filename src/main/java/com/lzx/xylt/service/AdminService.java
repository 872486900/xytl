package com.lzx.xylt.service;

import com.lzx.xylt.domain.Admin;

/**
 * Created by 87248 on 2020-05-02 15:09
 */
public interface AdminService {
    Admin login(String adminName, String password);
}
