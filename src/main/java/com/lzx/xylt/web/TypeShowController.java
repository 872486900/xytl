package com.lzx.xylt.web;

import com.lzx.xylt.domain.Aritcle;
import com.lzx.xylt.domain.Type;
import com.lzx.xylt.service.AritcleService;
import com.lzx.xylt.service.TypeService;
import com.lzx.xylt.vo.AritcleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by 87248 on 2020-05-04 11:57
 */
@Controller
public class TypeShowController {
    @Autowired
    private AritcleService aritcleService;
    @Autowired
    private TypeService typeService;

    //展示分类
    @GetMapping("/types/{typeId}")
    public String types(
            @PathVariable Long typeId,
            @PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
            Model model
    ){
        List<Type> types=typeService.listTypeTop(10000);
        if (typeId==-1){
            typeId=types.get(0).getTypeId();
        }
        AritcleQuery aritcle = new AritcleQuery();
        aritcle.setTypeId(typeId);
        model.addAttribute("types",types);
        model.addAttribute("page",aritcleService.listAritcle(pageable,aritcle));
        model.addAttribute("activeTypeId",typeId);
        return "types";
    }

}
