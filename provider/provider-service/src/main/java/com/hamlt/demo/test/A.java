package com.hamlt.demo.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Getter
@Setter
//@Component
public class A implements InitializingBean , BeanPostProcessor {

    private Integer v = 0;

    //@Autowired
    private B b;

    @PostConstruct
    public void init() {
        v += 10;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        v += 10;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("A postProcessBeforeInitialization-------------------------->" + b.getV() + b); // 20
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        v += 10;
        System.out.println("A postProcessAfterInitialization-------------------------->" + b.getV() + b); // 20
        return bean;
    }

}
