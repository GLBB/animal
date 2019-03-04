package cn.gl.share_knowledge.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 连接redis 得到一个 aaa
     */
    @Test
    public void test1(){
        String aaa = redisTemplate.opsForValue().get("aaa");
        System.out.println(aaa);
    }

    /**
     * 尝试保存 hash
     */
    @Test
    public void test2(){
        redisTemplate.opsForHash().put("verify_code", "98233@qq.com", 1223+"");
    }
}
