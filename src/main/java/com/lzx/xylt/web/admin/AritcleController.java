package com.lzx.xylt.web.admin;

import com.lzx.xylt.domain.Aritcle;
import com.lzx.xylt.service.AritcleService;
import com.lzx.xylt.service.TagService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by 87248 on 2020-05-02 21:02
 */
@Controller
@RequestMapping("/admin")
public class AritcleController {
    @Autowired
    private AritcleService aritcleService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    //获取所有文章
    @GetMapping("/aritcles")
    public String aritcle(@PageableDefault(size = 5, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                          Model model
    ) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page",aritcleService.listAritcle(pageable));
        return "admin/aritcles";
    }

    //管理员搜索功能
    @PostMapping("/aritcles/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model,
                         AritcleQuery aritcle
                         ) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", aritcleService.listAritcle(pageable,aritcle));
        return "admin/aritcles :: aritcleList";
    }

    //根据id查看文章
    @GetMapping("/aritcles/{artId}")
    public String show(@PathVariable Long artId,Model model){
        model.addAttribute("aritcle",aritcleService.getAritcle(artId));
        return "admin/aritcleShow";
    }

    //根据用户id 查看所有该用户的文章
    @GetMapping("/users/{userId}")
    public String aritcle(@PageableDefault(size = 5, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                          Model model,
                          @PathVariable Long userId
    ){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page",aritcleService.listAritcle(userId,pageable));
        return "admin/aritcles";
    }

    //删除文章
    @GetMapping("/aritcles/{airId}/delete")
    public String deleteAirtcle(@PathVariable Long airId,
                                RedirectAttributes attributes
                                ){
        aritcleService.deleteAritcle(airId);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/aritcles";

    }
    //关闭评论




}
