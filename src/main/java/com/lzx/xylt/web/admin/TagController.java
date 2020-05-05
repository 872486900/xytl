package com.lzx.xylt.web.admin;

import com.lzx.xylt.domain.Tag;
import com.lzx.xylt.service.TagService;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
 * Created by 87248 on 2020-05-02 17:40
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    //分页显示标签
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5,sort = {"tagId"},direction = Sort.Direction.DESC)Pageable pageable,
                       Model model
                       ){
        //分页显示
        Page<Tag> tags = tagService.listTag(pageable);
        model.addAttribute("page",tags);
        return "admin/tags";
    }
    @GetMapping("/tags/input")
    public String inputTag(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }
    @PostMapping("/tags")
    public String inputTag(@Valid Tag tag,
                           BindingResult result,
                           RedirectAttributes attributes){
        Tag t = tagService.getTagName(tag.getTagName());
        if (t != null) {
            result.rejectValue("tagName", "tagNameError", "不能添加重复标签");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag savaTag = tagService.savaTag(tag);
        if (savaTag==null){
            attributes.addFlashAttribute("message", "添加失败");
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/tags";
    }
    //修改标签
    @GetMapping("/tags/{tagId}/input")
    public String updateTag(@PathVariable Long tagId,
                            Model model
                            ){
        model.addAttribute("tag",tagService.getTag(tagId));
        return "admin/tags-input";
    }
    @PostMapping("/tags/{tagId}")
    public String updateTag(@Valid Tag tag,
                            @PathVariable Long tagId,
                            BindingResult result,
                            RedirectAttributes attributes
    ){
        Tag t = tagService.getTagName(tag.getTagName());
        if (t != null) {
            result.rejectValue("tagName", "tagNameError", "不能添加重复标签");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag savaTag = tagService.updateTag(tagId,tag);
        if (savaTag==null){
            attributes.addFlashAttribute("message", "添加失败");
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/tags";
    }



    //删除标签
    @GetMapping("/tags/{tagId}/delete")
    public String deleteTag(@PathVariable Long tagId,RedirectAttributes attributes){
        tagService.deleteTag(tagId);
        attributes.addFlashAttribute("message", "标签删除成功");
        return "redirect:/admin/tags";
    }

}
