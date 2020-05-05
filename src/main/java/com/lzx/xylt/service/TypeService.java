package com.lzx.xylt.service;

import com.lzx.xylt.Repository.TypeRepository;
import com.lzx.xylt.domain.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by 87248 on 2020-05-01 19:01
 */
public interface TypeService {
    //根据TypeId查询分类
    Type getType(Long typeId);
    //查询所有分类
    List<Type> listType();
    //查询分类并分类显示
    Page<Type> listType(Pageable pageable);
    //查询分类名称
    Type getTypeByName(String typeNMame);
    //新增分类
    Type savaType(Type type);

    void deleteType(Long typeId);
    //修改分类
    Type updateType(Long typeId, Type type);

    //展示多少个分类
    List<Type> listTypeTop(Integer size);
}
