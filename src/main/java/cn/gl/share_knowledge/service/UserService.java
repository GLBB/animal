package cn.gl.share_knowledge.service;

import cn.gl.share_knowledge.bean.User;
import cn.gl.share_knowledge.dto.UserDTO;
import cn.gl.share_knowledge.exception.RegisterException;
import cn.gl.share_knowledge.redis.EmailRepository;
import cn.gl.share_knowledge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailRepository emailRepository;

    /**
     * 查询验证码，对比验证码是否相同：
     * 相同，保存用户
     * 不相同，返回错误
     * 保存用户
     * @param user
     * @return
     */
    public User registerUser(UserDTO user) throws RegisterException {
        String code = emailRepository.getRegisterVerifyCode(user.getEmail());
        if (!code.equals(user.getCode())){
            throw new RegisterException("验证码错误");
        }
        User convertUser = user.converToUser();
        User save = userRepository.save(convertUser);
        return save;
    }

}
