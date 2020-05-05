package com.lzx.xylt.Repository;

import com.lzx.xylt.domain.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 87248 on 2020-05-01 11:51
 */
@Repository
public interface TagRepository  extends JpaRepository<Tag,Long> {

    //查询标签名字
    Tag findByTagName(String tagName);
    @Query("select t from Tag  t")
    List<Tag> findTopBy(PageRequest pageable);
    @Query("select t from Tag  t")
    List<Tag> findTop(PageRequest pageable);
}
