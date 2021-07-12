package com.hamlt.demo.zk;

import com.hamlt.demo.zk.snowflake.SnowflakeUidGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UidGeneratorConfig {

    @Bean
    public UidGenerator uidGenerator(UidGeneratorProperties properties){
        return new SnowflakeUidGenerator(properties.getZkAddress(), properties.getPort());
    }
}
