package com.lzx.xylt.web.admin;

import com.lzx.xylt.domain.Type;
import com.lzx.xylt.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by 87248 on 2020-05-02 17:41
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    //分页显示分类
    @GetMapping("/types")
    public String types(@PageableDefault(size = 5, sort = {"typeId"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        //分页显示
        Page<Type> types = typeService.listType(pageable);
        model.addAttribute("page", types);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    //@Valid判断校验的结果
    @PostMapping("/types")
    public String post(@Valid Type type,
                       BindingResult result,
                       RedirectAttributes attributes

    ) {
        Type t = typeService.getTypeByName(type.getTypeName());
        if (t != null) {
            result.rejectValue("typeName", "typeNameError", "不能添加重复分类");
        }
        if (result.hasErrors()) {
            return "admin/type-input";
        }
        Type savaType = typeService.savaType(type);
        if (savaType == null) {
            attributes.addFlashAttribute("message", "添加失败");
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/types";

    }

    //修改分类
    @GetMapping("/types/{typeId}/input")
    public String updateType(@PathVariable Long typeId,
                             Model model
    ) {
        model.addAttribute("type", typeService.getType(typeId));;
        return "admin/type-input";
    }

    @PostMapping("/types/{typeId}")
    public String updateType(@Valid Type type,
                            @PathVariable Long typeId,
                            BindingResult result,
                            RedirectAttributes attributes) {
        Type t = typeService.getTypeByName(type.getTypeName());
        if (t != null) {
            result.rejectValue("typeName", "typeNameError", "不能添加重复分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type update= typeService.updateType(typeId,type);
        if (update==null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";

    }


    //删除分类
    @GetMapping("/types/{typeId}/delete")
    public String deleteType(@PathVariable Long typeId, RedirectAttributes attributes) {
        typeService.deleteType(typeId);
        attributes.addFlashAttribute("message", "分类删除成功");
        return "redirect:/admin/types";

    }

}
