package com.hamlt.demo.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
@Setter
//@Component
public class B implements InitializingBean, BeanPostProcessor {

    //@Autowired
    private A a;

    private Integer v = 0;

    @PostConstruct
    public void init() {
        v += 10;
        System.out.println("B PostConstruct-------------------------->" + a.getV() + a); // 0
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        v += 10;
        System.out.println("B afterPropertiesSet-------------------------->" + a.getV() + a); // 0
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("B postProcessBeforeInitialization-------------------------->" + a.getV() + a); // 20
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        v += 10;
        System.out.println("B postProcessAfterInitialization-------------------------->" + a.getV()); // 30
        return bean;
    }

}
