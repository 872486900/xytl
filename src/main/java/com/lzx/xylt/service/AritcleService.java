package com.lzx.xylt.service;

import com.lzx.xylt.domain.Aritcle;
import com.lzx.xylt.domain.User;
import com.lzx.xylt.vo.AritcleQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by 87248 on 2020-05-01 18:32
 */
public interface AritcleService {
    //发布文章
    Aritcle saveAritcle(Aritcle aritcle);
    //修改文章
    Aritcle updateAritcle(Long ariId,Aritcle aritcle);
    //展示所有文章 分页
    Page<Aritcle> listAritcle(Pageable pageable);
    //根据用户id 查找该用户发的文章
    Page<Aritcle> listAritcle(Long userId,Pageable pageable);
    //搜索功能显示 标题+分类
    Page<Aritcle> listAritcle(Pageable pageable, AritcleQuery aritcle);
    //搜索功能 like 内容和标题
    Page<Aritcle> listAritcle(String query,Pageable pageable);
    //标签显示
    Page<Aritcle> listAritcle(Pageable pageable,Long tagId);

    //删除文章
    void deleteAritcle(Long airId);
    //根据id查看文章
    Aritcle getAritcle(Long airId);
    //根据用户id记录 改用户发布帖子个数
    Long countAritcle(Long userId);







}
