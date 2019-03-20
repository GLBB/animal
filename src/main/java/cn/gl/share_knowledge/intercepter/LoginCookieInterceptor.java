package cn.gl.share_knowledge.intercepter;

import cn.gl.share_knowledge.bean.User;
import cn.gl.share_knowledge.dto.UserLoginDTO;
import cn.gl.share_knowledge.exception.LoginException;
import cn.gl.share_knowledge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginCookieInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginCookieInterceptor#preHandle");

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user != null) return true;
        Cookie[] cookies = request.getCookies();
        Cookie emailCookie = null;
        Cookie pwdCookie = null;
        for (int i = 0; i < cookies.length; i++) {
            if ("email".equals(cookies[i].getName())){
                emailCookie = cookies[i];
            }else if ("password".equals(cookies[i].getName())) {
                pwdCookie = cookies[i];
            }
        }
        if (emailCookie == null || pwdCookie == null) return true;
        String email = emailCookie.getValue();
        String pwd = pwdCookie.getValue();
        if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty("pwd")) {
            UserLoginDTO userLoginDTO = UserLoginDTO.userDTO(email, pwd);
            try{
                User dbUser = userService.login(userLoginDTO);
                session.setAttribute("user", dbUser);
            }catch (LoginException e) {
                System.out.println("登陆失败");
                e.printStackTrace();
            }
        }
        return true;
    }
}
