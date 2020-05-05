package com.lzx.xylt.web;

import com.lzx.xylt.domain.Tag;
import com.lzx.xylt.service.AritcleService;
import com.lzx.xylt.service.TagService;
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
 * Created by 87248 on 2020-04-21 21:09
 */
@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;

    @Autowired
    private AritcleService aritcleService;


    @GetMapping("/tags/{tagId}")
    public String tags(
            @PageableDefault(size = 5,direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Long tagId,
            Model model
            ){
        List<Tag> tags = tagService.listTagTop(10000);
        if (tagId == -1) {
            tagId = tags.get(0).getTagId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", aritcleService.listAritcle(pageable,tagId));
        model.addAttribute("activeTagId", tagId);
        return "tags";
    }
}
