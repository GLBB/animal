package cn.gl.share_knowledge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("admin")
    public String goAdminPage(){
        return "/user/admin-index";
    }
}
