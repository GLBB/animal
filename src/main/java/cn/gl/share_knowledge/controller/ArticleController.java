package cn.gl.share_knowledge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArticleController {

    @RequestMapping("/article/writearticle")
    public String writeArticle(){
        return "/article/write_article";
    }
}
