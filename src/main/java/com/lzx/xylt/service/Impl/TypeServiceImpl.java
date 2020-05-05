package com.lzx.xylt.service.Impl;

import com.lzx.xylt.NotFoundException;
import com.lzx.xylt.Repository.TypeRepository;
import com.lzx.xylt.domain.Type;
import com.lzx.xylt.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 87248 on 2020-05-01 19:02
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type getType(Long typeId) {
        return typeRepository.getOne(typeId);
    }

    //查询所有分类
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    //查询分类并分类显示
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }
    //查询分类名称
    @Override
    public Type getTypeByName(String typeName) {
        return typeRepository.findByTypeName(typeName);
    }
    //新增分类
    @Override
    public Type savaType(Type type) {
        Type type1 = typeRepository.save(type);
        return type1;
    }

    @Override
    public void deleteType(Long typeId) {
        typeRepository.deleteById(typeId);
    }

    //根基id修改分类
    @Override
    public Type updateType(Long typeId, Type type) {
        Type t = typeRepository.getOne(typeId);
        if (t==null){
            throw  new NotFoundException("不存在该类");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    //展示多少个分类
    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"aritcles.size");
        PageRequest pageable = PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }


}
