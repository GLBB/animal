package cn.gl.share_knowledge.service;

import cn.gl.share_knowledge.redis.EmailRepository;
import cn.gl.share_knowledge.util.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    JavaMailSenderImpl sender;

    @Autowired
    EmailRepository emailRepository;


    /**
     * 发送注册邮件，
     * 并将验证码保存到redis, 过期时间一分钟
     * 返回值为true 表示一切正常
     *
     * @param email
     */
    public boolean registerEmail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender.getUsername());
        message.setTo(email);
        message.setSubject("邮箱注册邮件");
        String code = VerifyCode.registerCode();
        String content = "验证码: " + code;
        message.setText(content);
        try {
            sender.send(message);
            emailRepository.saveRegisterVerifyCode(email, code);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
        }
        return false;
    }
}
