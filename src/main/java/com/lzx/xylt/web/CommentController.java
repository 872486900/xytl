package com.lzx.xylt.web;

import com.lzx.xylt.domain.Aritcle;
import com.lzx.xylt.domain.Comment;
import com.lzx.xylt.domain.User;
import com.lzx.xylt.service.AritcleService;
import com.lzx.xylt.service.CommentService;
import com.lzx.xylt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by 87248 on 2020-05-04 23:40
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private AritcleService aritcleService;
    @Autowired
    private UserService userService;


    @GetMapping("/comments/{artId}")
    public String comments(@PathVariable Long artId, Model model) {
        model.addAttribute("comments", commentService.listCommentByAritcle(artId));
        return "aritcle :: commentList";
    }
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long artId = comment.getAritcle().getArtId();
        comment.setAritcle(aritcleService.getAritcle(artId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getUserAvatar());
            commentService.saveComment(comment);
        }
        return "redirect:/comments/" + artId;
    }


}
