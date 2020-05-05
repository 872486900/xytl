package com.lzx.xylt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 87248 on 2020-05-01 11:13
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;
    @NotBlank(message = "分类名称不能为空")
    private String typeName;

    //一个分类拥有多个文章
    @OneToMany(mappedBy = "type")
    private List<Aritcle> aritcles = new ArrayList<>();




}
