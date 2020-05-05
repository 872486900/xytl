package com.lzx.xylt.web.admin;

import com.lzx.xylt.domain.User;
import com.lzx.xylt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by 87248 on 2020-05-03 14:22
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private UserService userService;

    //获取用户信息
    @GetMapping("/users")
    public String users(Model model,
                        @PageableDefault(size = 5,sort = {"userId"},direction = Sort.Direction.DESC)Pageable pageable
                        ){
        Page<User> users = userService.listUser(pageable);
        model.addAttribute("page",users);
        return "admin/users";
    }

    //删除用户
    @GetMapping("/users/{userId}/delete")
    public String delete(@PathVariable Long userId,
                         RedirectAttributes attributes){
        userService.deleteUser(userId);
        attributes.addFlashAttribute("message","用户删除成功");
        return "redirect:/admin/users";
    }
}
