package com.hamlt.demo.rocketmq;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class RocketMqConfig {

    @Resource
    private DefaultMQProducer mqProducer;

    @Bean
    public MappingJackson2MessageConverter mappingJackson2MessageConverter(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MappingJackson2MessageConverter jackson = new MappingJackson2MessageConverter();
        jackson.setObjectMapper(mapper);
        return jackson;
    }

    @Bean
    public RocketMQTemplate rocketMQTemplate(){
        RocketMQTemplate template = new RocketMQTemplate();
        template.setMessageConverter(mappingJackson2MessageConverter());
        template.setProducer(mqProducer);
        /**
         * 设置有序消息，消息队列选择器
         */
        template.setMessageQueueSelector((mqs, message, hashKey) -> {
            int index = 0;
            try {
                // 如果hashKey非整型，则默认选第一个队列
                index = Integer.valueOf(String.valueOf(hashKey)) % mqs.size();
            }catch (Exception ex){

            }
            return mqs.get(index);
        });
        return template;
    }

}
