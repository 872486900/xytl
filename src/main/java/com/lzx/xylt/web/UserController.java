package com.lzx.xylt.web;

import com.lzx.xylt.domain.User;
import com.lzx.xylt.service.AritcleService;
import com.lzx.xylt.service.TagService;
import com.lzx.xylt.service.TypeService;
import com.lzx.xylt.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by 87248 on 2020-05-01 13:41
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private AritcleService aritcleService;
    @Autowired
    private TagService tagService;

    @Value("${user.userAvatar}")
    private String userAvatar;



    @GetMapping("/login")
    public String userLogin() {
        return "login";
    }

    @GetMapping("/regist")
    public String userRegist() {
        return "regist";
    }

    //登录
    @PostMapping("/login")
    public String userLoign(@RequestParam String userEmail,
                            @RequestParam String userPassword,
                            HttpSession session,
                            RedirectAttributes attributes,
                            Model model) {

        User user = userService.userLogin(userEmail, userPassword);
        if (user != null) {
            user.setUserPassword(null);
            session.setAttribute("user", user);
            attributes.addFlashAttribute("message","登录成功");
            System.out.println("登录成功");
            return "redirect:/";
        } else {
            model.addAttribute("message", "用户登录失败");
            System.out.println("用户登录失败");
            return "login";
        }
    }

    //注册//修改
    @PostMapping("regist")
    public String userRegist(@Valid User user,
                             BindingResult result,
                             RedirectAttributes attributes,
                             Model model
    ) {
        User u1 = userService.getUserEmail(user.getUserEmail());
        if (u1 != null) {
            result.rejectValue("userEmail", "userEmailError", "邮箱已经注册，请重新换邮箱");
            model.addAttribute("message", "邮箱已经注册，请更换邮箱");
            System.out.println("邮箱已经注册，请重新换邮箱");
        }
        if (result.hasErrors()) {
            return "regist";
        }

        user.setUserTime(new Date());
        user.setUserAvatar(userAvatar);
        User u2 = userService.userRegist(user);
        if (u2 == null) {
            attributes.addFlashAttribute("message", "注册失败");
            System.out.println("注册失败");
        } else {
            attributes.addFlashAttribute("message", "注册成功，请登录");
            System.out.println("注册成功");
        }
        return "redirect:login";
    }

    //退出
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        System.out.println("退出登录");
        return "redirect:/";

    }

    //用户修改用户信息
    @GetMapping("/about")
    public String updateUser(HttpSession session, Model model) {
        User u1 = (User) session.getAttribute("user");
        Long userId = u1.getUserId();
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "about";
    }
    @PostMapping("/about/{userId}")
    public String updateUser(@Valid User user,
                             @PathVariable Long userId,
                             BindingResult result,
                             HttpSession session,
                             Model model
    ) {

        String avatar="/images/"+user.getUserAvatar();
        session.setAttribute("user",user);
        user.setUserAvatar(avatar);
        user.setUserTime(new Date());
        User user1 = userService.updateUser(userId, user);
        if (user1 == null) {
            model.addAttribute("message", "修改失败");
            System.out.println("修改失败");
        } else {
            model.addAttribute("message", "用户修改成功");
            System.out.println("用户修改成功");
        }
        return "about";

    }

}
