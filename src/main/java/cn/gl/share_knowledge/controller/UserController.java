package cn.gl.share_knowledge.controller;

import cn.gl.share_knowledge.bean.User;
import cn.gl.share_knowledge.dto.UserDTO;
import cn.gl.share_knowledge.exception.RegisterException;
import cn.gl.share_knowledge.message.Message;
import cn.gl.share_knowledge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

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
     * @param userDTO
     * @return
     */
    @PostMapping("/users")
    public ModelAndView registerUser(@Validated UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            ModelAndView modelAndView = new ModelAndView("/user/register");
            modelAndView.addObject("message", fieldError.getDefaultMessage());
            return modelAndView;
        }
        // 保存用户到数据库
        User saveUser = null;
        try {
            saveUser = userService.registerUser(userDTO);
        } catch (RegisterException e) {
            ModelAndView modelAndView = new ModelAndView("/user/register");
            modelAndView.addObject("message", e.getMessage());
        }
        ModelAndView modelAndView = new ModelAndView("/user/login");
        modelAndView.addObject("user", saveUser);
        return modelAndView;
    }

    @GetMapping("/login")
    public String login(User user){
        return "user/login";
    }
}
