package com.hamlt.demo.controller;

import com.hamlt.demo.api.pojo.EchoReq;
import com.hamlt.demo.api.pojo.EchoResp;
import com.hamlt.demo.client.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ConsumerTestController {

    @Autowired
    private ProviderClient providerClient;

    @PostMapping("echo")
    public EchoResp testEcho(@RequestBody EchoReq req) {
        return providerClient.echo(req);
    }

}
