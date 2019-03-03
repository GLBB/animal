package cn.gl.share_knowledge.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailConfigTest {
    @Autowired
    EmailConfig emailConfig;

    @Autowired
    MailSender mailSender;

    @Value("${mailserver.host}")
    String host;

    @Test
    public void test1(){
        System.out.println(host);
        System.out.println();
    }

}