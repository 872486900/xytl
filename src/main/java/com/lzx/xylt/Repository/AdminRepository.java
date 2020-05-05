package com.lzx.xylt.Repository;

import com.lzx.xylt.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 87248 on 2020-05-01 11:51
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByAdminNameAndPassword(String adminName, String password);
}
