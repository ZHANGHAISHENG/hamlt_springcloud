package com.hamlt.demo.api.service;

import com.hamlt.demo.api.pojo.EchoReq;
import com.hamlt.demo.api.pojo.EchoResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProviderRemoteService {

    @PostMapping("/test/echo")
    EchoResp echo(@RequestBody EchoReq request);

}
