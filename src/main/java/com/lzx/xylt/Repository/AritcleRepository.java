package com.lzx.xylt.Repository;

import com.lzx.xylt.domain.Aritcle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 87248 on 2020-05-01 11:52
 */
@Repository
public interface AritcleRepository extends JpaRepository<Aritcle,Long> {
    //根据用户id 查找该用户发的文章
    @Query("select a from Aritcle a where a.user.userId= ?1")
    Page<Aritcle> findByUser(Pageable pageable,Long userId);

    //搜索功能显示 分页
    Page<Aritcle> findAll(Specification<Aritcle> title, Pageable pageable);

    ///根据用户id记录 改用户发布帖子个数
    @Query("select count(a) from Aritcle a where a.user.userId= ?1")
    Long countAritcle(Long userId);

    //搜索功能 like 内容和标题
    @Query("select  a from Aritcle  a where a.title like ?1 or a.content like ?1")
    Page<Aritcle> findByQuery(String query,Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Aritcle  a set a.view=a.view+1 where a.artId=?1")
   int updateView(Long airId);
}
