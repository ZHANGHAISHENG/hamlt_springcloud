package com.hamlt.demo.controller;

import com.hamlt.demo.api.pojo.EchoReq;
import com.hamlt.demo.api.pojo.EchoResp;
import com.hamlt.demo.api.service.ProviderRemoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "测试接口")
@RestController
public class ProviderTestController implements ProviderRemoteService {

    @Value("${defaultUserName:zhs}")
    private String defaultUserName;

    @ApiOperation(value = "简单输出信息")
    @Override
    public EchoResp echo(@RequestBody  EchoReq request) {
        return new EchoResp("hello! thanks for your request: " + request.getContent() + ", default userName:" + defaultUserName);
    }

    @PostMapping("/test/reqTest")
    public String reqTest(HttpServletRequest request) {
        Object userName = request.getHeader("userName");
        return userName == null ? "empty userName" : userName.toString();
    }

}
