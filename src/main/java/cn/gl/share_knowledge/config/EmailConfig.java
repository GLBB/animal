package cn.gl.share_knowledge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Configuration
@PropertySource("classpath:config/email.properties")
public class EmailConfig {

    @Value("${mailserver.host}")
    private String host;
    @Value("${mailserver.port}")
    private Integer port;
    @Value("${mailserver.username}")
    private String username;
    @Value("${mailserver.password}")
    private String password;

    @Bean
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setDefaultEncoding("UTF-8");
        mailSenderImpl.setHost(host);
        mailSenderImpl.setPort(Integer.valueOf(port));
        mailSenderImpl.setUsername(username);
        mailSenderImpl.setPassword(password);
        return mailSenderImpl;
    }

}
