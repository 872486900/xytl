package com.lzx.xylt.service;

import com.lzx.xylt.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by 87248 on 2020-05-01 19:01
 */
public interface TagService {
    //根据Id查询分类s
    public Tag getTag(Long tagId);
   //查询所有便签
   List<Tag> listTag();
    //查询所有便签 多个便签展示1,2,3
    List<Tag> listTags(String tagIds);


    //查询标签并分页显示
    Page<Tag> listTag(Pageable pageable);
    //查询标签名称
    Tag getTagName(String tagName);
    //新增分类
    Tag savaTag(Tag tag);

    void deleteTag(Long tagId);
    //修改分类
    Tag updateTag(Long tagId, Tag tag);
    //展示多少个标签
    List<Tag> listTagTop(Integer size);

}
