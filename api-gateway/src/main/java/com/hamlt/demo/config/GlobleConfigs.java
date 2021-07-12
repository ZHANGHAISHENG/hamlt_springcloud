package com.hamlt.demo.config;

import com.hamlt.demo.filter.CustomGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Predicate;

/**
 * 获取body 参数
 * https://www.cnblogs.com/hyf-huangyongfei/p/12849406.html
 **/
@Configuration
public class GlobleConfigs {
    @Bean
    public GlobalFilter customGlobalFilter() {
        return new CustomGlobalFilter();
    }

    @Bean
    public Predicate bodyPredicate(){
        return o -> true;
    }

}
