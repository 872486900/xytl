package com.lzx.xylt.service.Impl;

import com.lzx.xylt.NotFoundException;
import com.lzx.xylt.Repository.AritcleRepository;
import com.lzx.xylt.domain.Aritcle;
import com.lzx.xylt.domain.Type;
import com.lzx.xylt.domain.User;
import com.lzx.xylt.service.AritcleService;
import com.lzx.xylt.vo.AritcleQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.criteria.*;
import java.util.*;
/**
 * Created by 87248 on 2020-05-01 18:33
 */
@Service
public class AritcleServiceImpl implements AritcleService {
    @Autowired
    private AritcleRepository aritcleRepository;

    //发布文章
    @Transactional
    @Override
    public Aritcle saveAritcle(Aritcle aritcle) {
        return aritcleRepository.save(aritcle);
    }

    //修改文章
    @Override
    public Aritcle updateAritcle(Long ariId, Aritcle aritcle) {
        return null;
    }

    //展示所有文章 分页
    @Override
    public Page<Aritcle> listAritcle(Pageable pageable) {
        return aritcleRepository.findAll(pageable);
    }
    //根据用户id 查找该用户发的文章
    @Override
    public Page<Aritcle> listAritcle(Long userId,Pageable pageable) {
        Page<Aritcle> byUser = aritcleRepository.findByUser(pageable,userId);
        return byUser;
    }

    //搜索功能显示 分页
    @Override
    public Page<Aritcle> listAritcle(Pageable pageable, AritcleQuery aritcle) {
        return aritcleRepository.findAll(new Specification<Aritcle>() {
            @Override
            public Predicate toPredicate(Root<Aritcle> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(aritcle.getTitle()) && aritcle.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+aritcle.getTitle()+"%"));
                }
                if (aritcle.getTypeId()!=null){
                    predicates.add(cb.equal(root.<Type>get("type").get("typeId"),aritcle.getTypeId()));
                }

                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    //搜索功能 like 内容和标题
    @Override
    public Page<Aritcle> listAritcle(String query, Pageable pageable) {
        return aritcleRepository.findByQuery(query,pageable);
    }

    //标签显示
    @Override
    public Page<Aritcle> listAritcle(Pageable pageable, Long tagId) {
        return aritcleRepository.findAll(new Specification<Aritcle>() {
            @Override
            public Predicate toPredicate(Root<Aritcle> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //关联对向
                Join join=root.join("tags");
                return criteriaBuilder.equal(join.get("tagId"),tagId);
            }
        },pageable);
    }



    //删除文章
    @Override
    public void deleteAritcle(Long airId) {
        aritcleRepository.deleteById(airId);
    }

    //根据id查看文章 增加阅读量
    @Override
    public Aritcle getAritcle(Long airId) {
//        return aritcleRepository.getOne(airId);
        Aritcle aritcle = aritcleRepository.getOne(airId);
        if (aritcle==null){
            throw new NotFoundException("该博客不存在");
        }
        aritcleRepository.updateView(airId);
        return aritcle;

    }

    //根据用户id记录 改用户发布帖子个数
    @Override
    public Long countAritcle(Long userId) {
        return aritcleRepository.countAritcle(userId);
    }


}

