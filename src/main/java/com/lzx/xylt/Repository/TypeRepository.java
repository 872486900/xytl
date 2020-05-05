package com.lzx.xylt.Repository;

import com.lzx.xylt.domain.Type;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 87248 on 2020-05-01 11:51
 */
@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    //查询分类名字
    Type findByTypeName(String typeName);

    @Query("select t from Type  t")
    List<Type> findTop(PageRequest pageable);
}
