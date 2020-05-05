package com.lzx.xylt.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 87248 on 2020-05-02 23:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AritcleQuery {
    private String title;
    private Long typeId;

}
