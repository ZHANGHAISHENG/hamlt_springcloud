package com.hamlt.demo.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.AbstractMap;

@Getter
@Setter
//@Component
public class C implements InitializingBean, BeanPostProcessor {


    private Integer v = 0;

    @PostConstruct
    public void init() {
        v += 10;
        System.out.println("C PostConstruct-------------------------->" + v); // 0
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        v += 10;
        System.out.println("C afterPropertiesSet-------------------------->" + v); // 0
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("C postProcessBeforeInitialization-------------------------->" + v); // 20
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        v += 10;
        System.out.println("C postProcessAfterInitialization-------------------------->" + v); // 30
        return bean;
    }

}
