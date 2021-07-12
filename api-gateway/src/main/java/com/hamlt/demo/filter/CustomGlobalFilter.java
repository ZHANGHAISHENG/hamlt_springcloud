package com.hamlt.demo.filter;

import com.hamlt.demo.utils.JwtTokenUtils;
import com.hamlt.demo.utils.ReadUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Administrator
 * @date 2020-07-17 0:26
 **/
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //  int i = 1/0;
        ServerHttpRequest serverHttpRequest = exchange.getRequest();

        String userName;
        try {
            userName = getUserName(serverHttpRequest);
        } catch (Exception ex) {
            return Mono.error(ex);
        }
        // 设置头部信息
        String finalUserName = userName;
        Consumer<HttpHeaders> headersConsumer = x -> {
            x.add("headerTest", "1");
            if(finalUserName != null) {
                x.add("userName", finalUserName);
            }
        };
        serverHttpRequest.mutate().headers(headersConsumer).build();

        String method = serverHttpRequest.getMethodValue();
        if ("POST".equals(method)) {
            Object requestBody = exchange.getAttribute("cachedRequestBodyObject");
            System.out.println("request:" + requestBody);
        } else if ("GET".equals(method)) {
            MultiValueMap<String, String> queryParams = serverHttpRequest.getQueryParams();
            System.out.println("request:" + queryParams);
            return chain.filter(exchange);
        }
        ServerHttpResponseDecorator decoratedResponse = ReadUtils.readResponse(exchange.getResponse());
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    private String getUserName(ServerHttpRequest serverHttpRequest) throws Exception {
        List<String> tokenHeaders = serverHttpRequest.getHeaders().get("token");
        String userName = null;
        if(tokenHeaders != null && tokenHeaders.size() > 0) {
            String token = tokenHeaders.get(0);
            if(JwtTokenUtils.isExpiration(token)) {
               throw new Exception("token已过期");
            }
            userName = JwtTokenUtils.getUserName(token);
        }
        return userName;
    }

    @Override
    public int getOrder() {
        return -1;
    }

}