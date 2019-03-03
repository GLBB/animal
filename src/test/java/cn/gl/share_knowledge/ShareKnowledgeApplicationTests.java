package cn.gl.share_knowledge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShareKnowledgeApplicationTests {

    @Autowired
    Environment env;

//    @Value("${mailserver.host}")
    String host;

    @Test
    public void contextLoads() throws IOException {
        System.out.println(host);
    }

}
