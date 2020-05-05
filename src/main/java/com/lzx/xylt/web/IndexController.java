package com.lzx.xylt.web;

import com.lzx.xylt.service.AritcleService;
import com.lzx.xylt.service.TagService;
import com.lzx.xylt.service.TypeService;
import com.lzx.xylt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 87248 on 2020-05-04 14:30
 */
@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private AritcleService aritcleService;
    @Autowired
    private TagService tagService;
    //首页控制器
    @GetMapping("/")
    public String index(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page",aritcleService.listAritcle(pageable));
        model.addAttribute("types",typeService.listTypeTop(5));
        model.addAttribute("tags",tagService.listTagTop(5));
        return "index";
    }
    //搜索功能
    @PostMapping("/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query,
                         Model model) {
        model.addAttribute("page",aritcleService.listAritcle("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "/search";

    }

}
