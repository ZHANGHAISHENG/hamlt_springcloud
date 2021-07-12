package com.hamlt.demo.listener;

import lombok.extern.java.Log;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Log
@Component
@RocketMQMessageListener(topic = "test_topic_26", consumerGroup = "group2")
public class RocketMqDemoListener implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
       byte[] body = messageExt.getBody();
       String msg = new String(body);
       System.out.println("receive sync message:" + msg);
    }

}
