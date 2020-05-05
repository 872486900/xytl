package com.lzx.xylt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 87248 on 2020-05-01 9:56
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userSex;
    private String userPhone;
    private boolean userStatus;//用户是否激活
    private String userAvatar;//用户头像
    private String userShow;//用户个性签名
    @Temporal(TemporalType.TIMESTAMP)
    private Date userTime;//更新时间

    //一个用户可以发布多篇文章
    @OneToMany(mappedBy = "user")
    private List<Aritcle> aritcles = new ArrayList<>();





}
