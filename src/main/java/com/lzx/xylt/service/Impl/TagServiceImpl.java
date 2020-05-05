package com.lzx.xylt.service.Impl;

import com.lzx.xylt.NotFoundException;
import com.lzx.xylt.Repository.TagRepository;
import com.lzx.xylt.domain.Tag;
import com.lzx.xylt.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 87248 on 2020-05-01 19:02
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    //根据Tagid查询所有
    @Transactional
    @Override
    public Tag getTag(Long tagId) {
        return tagRepository.getOne(tagId);
    }

    //查询所有便签
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    //多个标签展示1,2,3
    @Override
    public List<Tag> listTags(String tagIds) {
        return tagRepository.findAllById(converTolist(tagIds));
    }
    //多个标签展示
    private List<Long> converTolist(String ids){
        List<Long> list=new ArrayList<>();
        if (!"".equals(ids)&& ids!=null){
            String [] idarray=ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));

            }
        }
        return list;
    }

    //查询标签并分页显示
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    //查询标签名称
    @Override
    public Tag getTagName(String tagName) {
        return tagRepository.findByTagName(tagName);
    }

    //新增标签
    @Override
    public Tag savaTag(Tag tag) {
        Tag save = tagRepository.save(tag);
        return save;
    }

    @Override
    public void deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);

    }
    //修改标签
    @Override
    public Tag updateTag(Long tagId, Tag tag) {
        Tag t = tagRepository.getOne(tagId);
        if (t==null){
            throw  new NotFoundException("不存在该类");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    //用户展示标签
    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"aritcles.size");
        PageRequest pageable = PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);
    }


}
