package cn.gl.share_knowledge.repository.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void test1(){
        String aaa = redisTemplate.opsForValue().get("aaa");
        System.out.println(aaa);
    }
}
