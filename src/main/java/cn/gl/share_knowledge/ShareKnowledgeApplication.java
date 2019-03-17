package cn.gl.share_knowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * wait do: 相同 email 只能注册一次
 */
@SpringBootApplication
public class ShareKnowledgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareKnowledgeApplication.class, args);
    }

}
