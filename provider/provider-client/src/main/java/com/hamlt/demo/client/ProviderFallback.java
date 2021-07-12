package com.hamlt.demo.client;

import com.hamlt.demo.api.pojo.EchoReq;
import com.hamlt.demo.api.pojo.EchoResp;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Log
@Component
public class ProviderFallback implements ProviderClient {

    @Override
    public EchoResp echo(@RequestBody EchoReq request) {
        log.info("熔断：" + request.getContent());
        return new EchoResp("please wait..");
    }

}
