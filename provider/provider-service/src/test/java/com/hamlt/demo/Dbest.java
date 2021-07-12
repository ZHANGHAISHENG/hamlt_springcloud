package com.hamlt.demo;


import com.hamlt.demo.product.entity.Orders;
import com.hamlt.demo.product.mapper.ex.OrdersMapperEx;
import com.hamlt.demo.user.entity.Auth;
import com.hamlt.demo.user.mapper.ex.AuthMapperEx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Dbest {

    @Resource
    private AuthMapperEx authMapperEx;

    @Resource
    private OrdersMapperEx ordersMapperEx;


    @Test
    public void test1() {
        Auth auth = new Auth();
        auth.setName("ls");
        int i = authMapperEx.insert(auth);
        System.out.println(i);
    }

    @Test
    public void test2() {
        Orders x = new Orders();
        x.setDescription("hello");
        int i = ordersMapperEx.insert(x);
        System.out.println(i);
    }


}
