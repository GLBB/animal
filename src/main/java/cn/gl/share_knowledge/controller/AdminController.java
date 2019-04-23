package cn.gl.share_knowledge.controller;

import cn.gl.share_knowledge.bean.Article;
import cn.gl.share_knowledge.bean.User;
import cn.gl.share_knowledge.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    ArticleService articleService;

    // 判断是否登陆, 未登录，去登陆
    // 已登陆，根据 email 获得所有 article, 将信息携带到 admin-index 页面
    @RequestMapping("admin")
    public String goAdminPage(HttpSession session){
        Object user = session.getAttribute("user");
        if (user == null)
            return "redirect:/login";
        if (!(user instanceof User)){
            return "redirect:/login";
        }
        User realUser = (User) user;
        Object articles = session.getAttribute("allArticles");
        List<Article> allArticles = articleService.getAllByEmail(realUser.getEmail());
        session.setAttribute("allArticles", allArticles);
        return "/user/admin-index";
    }
}
