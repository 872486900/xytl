package com.lzx.xylt.web.admin;

import com.lzx.xylt.domain.Admin;
import com.lzx.xylt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by 87248 on 2020-05-02 15:09
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String adminName,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes,
                        Model model
    ){
        Admin admin= adminService.login(adminName,password);
        if (admin!=null){
            admin.setPassword(null);
            session.setAttribute("admin",admin);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","管理员账号或密码错误");
            return "redirect:/admin";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/admin";
    }
}
