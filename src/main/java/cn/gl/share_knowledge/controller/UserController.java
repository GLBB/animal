package cn.gl.share_knowledge.controller;

import cn.gl.share_knowledge.bean.User;
import cn.gl.share_knowledge.dto.UserDTO;
import cn.gl.share_knowledge.dto.UserLoginDTO;
import cn.gl.share_knowledge.exception.LoginException;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;

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

    /**
     * 用户输入邮箱和密码后登陆
     * 检验邮箱和密码对不对
     * 成功后保存 session
     * 保存 cookie
     * @param user
     * @return
     */
    @PostMapping("/login")
    public String userLogin(@Validated UserLoginDTO user, BindingResult bindingResult, Model model, HttpSession session, HttpServletResponse response){
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            model.addAttribute("err_msg", fieldError.getDefaultMessage());
            return "user/login";
        }

        try {
            User validateUser = userService.login(user);
            session.setAttribute("user", validateUser);
            // 保存 cookie, 保存 3 天
            if ("true".equals(user.getRemenberPassword())){
                Cookie emailCookie = new Cookie("email", user.getEmail());
                emailCookie.setPath("/");
                emailCookie.setMaxAge(60*60*24*3);
                emailCookie.setHttpOnly(true);
                response.addCookie(emailCookie);
                Cookie pwdCookie = new Cookie("password", user.getPassword());
                emailCookie.setPath("/");
                emailCookie.setMaxAge(60*60*24*3);
                emailCookie.setHttpOnly(true);
                response.addCookie(pwdCookie);
            }
            return "redirect:/";
        } catch (LoginException e) {
            model.addAttribute("err_msg", e.getMessage());
            return "user/login";
        }

    }
}
