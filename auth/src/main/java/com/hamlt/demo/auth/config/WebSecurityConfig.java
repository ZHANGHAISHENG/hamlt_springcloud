package com.hamlt.demo.auth.config;


import com.hamlt.demo.auth.handler.MyAccessDeniedHandler;
import com.hamlt.demo.auth.handler.MyAuthExceptionEntryPoint;
import com.hamlt.demo.auth.filter.JwtAuthenticationFilter;
import com.hamlt.demo.auth.service.ApiUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Autowired
    private ApiUserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 通过HttpSecurity实现Security的自定义过滤配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置token认证filter
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/auth/login", "/auth/mobile/login", "/auth/logout").permitAll() //permitAll 允许所有，  anonymous 代表不允许已登录用户访问
                .anyRequest().authenticated()
                //授权
                .and()
                // 禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 自定义异常处理
        http.exceptionHandling()
            .accessDeniedHandler(new MyAccessDeniedHandler())
            .authenticationEntryPoint(new MyAuthExceptionEntryPoint());

        //jwt过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //使用的密码比较方式
        return  new BCryptPasswordEncoder();
    }

}
