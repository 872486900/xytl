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
 * Created by 87248 on 2020-05-01 11:14
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "t_tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    @NotBlank(message = "标签名称不能为空")
    private String tagName;

    //一遍文章可以包含多个便签
    //一个标签可以包含多篇文章
    @ManyToMany(mappedBy = "tags")
    private List<Aritcle> aritcles = new ArrayList<>();

}
