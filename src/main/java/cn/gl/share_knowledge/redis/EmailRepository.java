package cn.gl.share_knowledge.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class EmailRepository {

    @Autowired
    StringRedisTemplate template;

    /**
     * 保存验证码 1 分钟
     * @param email
     * @param code
     */
    public void saveRegisterVerifyCode(String email, String code){
        template.opsForValue().set(email, code);
        template.expire(email, 5, TimeUnit.MINUTES);
    }

    /**
     * 当邮箱不存在返回空
     * @param email
     * @return
     */
    public String getRegisterVerifyCode(String email){
        String s = template.opsForValue().get(email);
        return s;
    }
}
