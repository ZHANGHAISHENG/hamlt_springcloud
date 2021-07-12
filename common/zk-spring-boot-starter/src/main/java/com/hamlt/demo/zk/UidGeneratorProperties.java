package com.hamlt.demo.zk;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class UidGeneratorProperties {

    @Value("${zookeeper.address}")
    private String zkAddress;

    @Value("${server.port}")
    private int port;
}
