package com.lzx.xylt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 87248 on 2020-05-01 10:59
 */
//文章类
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "t_aritcle")
public class Aritcle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artId;
    private String title;//标题
    private String content;//内容
    private String artAvatar;//图片
    private Integer view;//浏览数
    private boolean commentabled;//是否可以评论
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    //多篇文章属于一个分类
    @ManyToOne
    private Type type;
    //一遍文章可以包含多个便签
    //一个标签可以包含多篇文章
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();
    //一个用户发表多篇文章
    @ManyToOne
    private User user;

    @Transient//不会进行数据库的操作
    private String tagIds;

    //一个文章可以多条评论
    @OneToMany(mappedBy = "aritcle")
    private List<Comment> comments = new ArrayList<>();





}
