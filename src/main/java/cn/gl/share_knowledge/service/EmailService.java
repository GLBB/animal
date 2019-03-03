package cn.gl.share_knowledge.service;

import cn.gl.share_knowledge.util.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    JavaMailSenderImpl sender;

    /**
     * @param email
     */
    public boolean registerEmail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender.getUsername());
        message.setTo(email);
        message.setSubject("邮箱注册邮件");
        String content = "验证码: " + VerifyCode.registerCode();
        message.setText(content);
        try {
            sender.send(message);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
        }
        return false;
    }
}
