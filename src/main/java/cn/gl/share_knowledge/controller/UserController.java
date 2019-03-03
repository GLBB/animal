package cn.gl.share_knowledge.controller;

import cn.gl.share_knowledge.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    /**
     * 前往注册页面
     * @return
     */
    @GetMapping("/users")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView("user/register");
        return modelAndView;
    }

    /**
     * 注册用户，登记到数据库
     * 前往登陆页面
     * @param user
     * @return
     */
    @PostMapping("/users")
    public ModelAndView registerUser(User user){
        return null;
    }
}
