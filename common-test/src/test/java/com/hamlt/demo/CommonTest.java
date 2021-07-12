package com.hamlt.demo;

import com.hamlt.demo.redis.lock.RedissonDistributedLock;
import com.hamlt.demo.redis.template.RedisRepository;
import com.hamlt.demo.zk.UidGenerator;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTest {

    @Autowired
    private RedissonDistributedLock redissonDistributedLock;

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private UidGenerator uidGenerator;

    @Test
    public void test3() {
        RLock a = redissonDistributedLock.lock("a");
        System.out.println("hello");
        redissonDistributedLock.unlock(a);
    }

    @Test
    public void test4() {
        redisRepository.set("testKey", 1);
        System.out.println(redisRepository.get("testKey"));
    }

    @Test
    public void test5() {
        rocketMQTemplate.sendOneWayOrderly("test_topic_26", "hello", "a");
    }

    @Test
    public void test6() {
        long uid = uidGenerator.getUID();
        System.out.println(uid);
    }

    @Test
    public void test7() {
        Map<String, Object> map = new HashMap<>(10);
    }

}
