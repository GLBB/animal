package cn.gl.share_knowledge.controller;

import cn.gl.share_knowledge.bean.Article;
import cn.gl.share_knowledge.bean.User;
import cn.gl.share_knowledge.service.ArticleService;
import cn.gl.share_knowledge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    /**
     * 去写文章页面
     * @return
     */
    @RequestMapping("/article/writearticle")
    public String writeArticle(HttpSession session){
        Object user = session.getAttribute("user");
        session.setAttribute("article", null);
        if (user == null) {
            return "redirect:/login";
        }
        return "/article/write_article";
    }

    /**
     * 发布文章
     * * 检验文章title 是否重复, 重复返回，并给出错误提示
     * 1. 将文章保存到文件系统中，
     * 2. 更新数据库
     *
     * 待写，未写完
     */
    @PostMapping("article/post_article")
    public String postArticle(Article article, HttpSession session, HttpServletRequest request){
        if (article.getTitle()==null || article.getTitle().trim().equals("")){
            request.setAttribute("post_article_errror", "文件标题不能为空");
            return "/article/write_article";
        }
        Object user = session.getAttribute("user");
        if (user == null){
            request.setAttribute("post_article_errror", "先登录才能发表文章喔");
            return "redirect:/login";
        }
        User u = (User) user;
        String email = u.getEmail();
        List<Article> articles = articleService.getAllByEmail(email);
        for (int i = 0; i < articles.size(); i++) {
            if (article.getTitle().equals(articles.get(i).getTitle())){
                request.setAttribute("post_article_errror", "文件标题不能重复喔");
                return "/article/write_article";
            }
        }
        // 保存文章到本地磁盘, 保存数据库记录
        articleService.postArticle(email, article.getTitle(), article.getContent());
        return "redirect:/admin";
    }

    // 根据 id 从数据库查询文章记录,并从文件系统中读取博客
    // 如果查询到的 article 为空，去 admin
    // 将 article 加入用户 session 中, 去 edit
    @RequestMapping("/article/edit/{id}")
    public String editArticle(@PathVariable Long id, HttpSession session) throws IOException {
        Optional<Article> optArticle = articleService.getArticleById(id);
        if (optArticle.isPresent()){
            Article article = optArticle.get();
            session.setAttribute("article", article);
            String contentPath = article.getContentPath();
            List<String> list = Files.readAllLines(Path.of(contentPath));
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<list.size(); i++) {
                if (i < list.size() -1)
                    sb.append(list.get(i)+"\n");
                else
                    sb.append(list.get(i));
            }
//            System.out.println(sb.toString());
            article.setContent(sb.toString());
            return "/article/update_article";
        }else {
            return "redirect:/admin";
        }
    }

    @RequestMapping("/article/update_article")
    public String updateArticle(Article article, HttpSession session,HttpServletRequest request){
        if (!userService.isLogin(session))
            return "redirect:/login";
        Object user = session.getAttribute("user");
        if (user == null && !(user instanceof User)) {
            return "redirect:/login";
        }
        if (article.getTitle()==null || article.getTitle().trim().equals("")){
            request.setAttribute("post_article_errror", "文件标题不能为空");
            return "/article/update_article";
        }
        if (article.getId()==null){
            System.err.println("article id null error");
            return "/admin";
        }
        User validateUser = (User) user;
        if (!article.getPrevTitle().trim().equals(article.getTitle().trim())){
            if (articleService.titleAreadyExist(article, validateUser)){
                request.setAttribute("post_article_errror", "文件标题不能重复喔");
                return "/article/update_article";
            }else {
                // 删除文件系统中的文章
                articleService.deleteArticleContent(validateUser.getEmail(), article.getPrevTitle());
            }
        }
        // 保存文章，并更新记录
        articleService.updateArticle(article, validateUser);
        return "redirect:/admin";
    }


    /**
     * 删除文章， 得到用户，根据用户email查询文章，如果包含，就删除，否则空操作
     * 返回 admin 页面
     * @param id
     * @return
     */
    @RequestMapping("article/delete/{id}")
    public String deleteArticle(@PathVariable Long id, HttpSession session){
        Object user = session.getAttribute("user");
        if (user == null || !(user instanceof User)){
            return "redirect:/admin";
        }
        User ruser = (User) user;
        articleService.deleteArticle(ruser, id);
        return "redirect:/admin";
    }

}
