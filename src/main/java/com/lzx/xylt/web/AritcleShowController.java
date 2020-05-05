package com.lzx.xylt.web;

import com.lzx.xylt.domain.Aritcle;
import com.lzx.xylt.domain.User;
import com.lzx.xylt.service.AritcleService;
import com.lzx.xylt.service.TagService;
import com.lzx.xylt.service.TypeService;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by 87248 on 2020-05-01 17:55
 */
@Controller
@RequestMapping("/user")
public class AritcleShowController {
    @Autowired
    private AritcleService aritcleService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;


    //获取到标签和分类
    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    @GetMapping("/aritcle/input")
    public String inputAritcle(Model model){
        setTypeAndTag(model);
        model.addAttribute("aritcle",new Aritcle());
        return "aritcle-input";
    }
    //发布文章
    @PostMapping("/aritcle/input")
    public String inputAritcle(@Valid Aritcle aritcle,
                               HttpSession session,
                               RedirectAttributes attributes,
                               Model model
    ) {

        String avatar="/images/"+aritcle.getArtAvatar();
        aritcle.setArtAvatar(avatar);
        aritcle.setView(0);
        aritcle.setUser((User)session.getAttribute("user"));
        aritcle.setType(typeService.getType(aritcle.getType().getTypeId()));
        aritcle.setTags(tagService.listTags(aritcle.getTagIds()));
        Aritcle a;
        if (aritcle.getArtId() == null) {
            aritcle.setCreateTime(new Date());
            aritcle.setUpdateTime(new Date());
            a = aritcleService.saveAritcle(aritcle);
        } else {
            aritcle.setUpdateTime(new Date());
            a = aritcleService.updateAritcle(aritcle.getArtId(), aritcle);
        }
        if (a == null) {
            attributes.addFlashAttribute("message", "发布失败");
            System.out.println("发布失败");
        } else {
            attributes.addFlashAttribute("message", "发布成功");
            System.out.println("发布成功");
        }
        return "redirect:/";
    }
    //查看我发布的帖子
    @GetMapping("/myAritcle/{userId}")
    public String aritcle(@PageableDefault(size = 5, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                          Model model,
                          @PathVariable Long userId
    ){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("aritcleCount",aritcleService.countAritcle(userId));
        model.addAttribute("page",aritcleService.listAritcle(userId,pageable));
        return "myAritcle";
    }

    //根据id查看文章
    @GetMapping("/aritcle/{artId}")
    public String show(@PathVariable Long artId,Model model){
        model.addAttribute("aritcle",aritcleService.getAritcle(artId));
        return "aritcle";
    }
    //删除文章
    @GetMapping("/aritcles/{airId}/delete")
    public String deleteAirtcle(@PathVariable Long airId,
                                HttpSession session,
                                RedirectAttributes attributes
    ){
        aritcleService.deleteAritcle(airId);
        attributes.addFlashAttribute("message","删除成功");
        User user = (User) session.getAttribute("user");
        Long userId = user.getUserId();
        System.out.println(userId);
        return "redirect:/user/myAritcle/"+userId;

    }

}
