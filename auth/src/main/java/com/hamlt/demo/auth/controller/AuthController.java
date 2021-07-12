package com.hamlt.demo.auth.controller;

import com.hamlt.demo.auth.pojo.Login;
import com.hamlt.demo.auth.pojo.LoginOut;
import com.hamlt.demo.auth.pojo.MobileLogin;
import com.hamlt.demo.auth.utils.JwtTokenUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/test")
    public String test() {
        return "true";
    }

    @PostMapping("/login")
    public String login(@RequestBody Login login) {
        return JwtTokenUtils.createToken(login.getUserName());
    }

    @PostMapping("/mobileLogin")
    public String mobileLogin(@RequestBody MobileLogin mobileLogin) {
        return JwtTokenUtils.createToken("zhs");
    }

    @PostMapping("/loginOut")
    public String loginOut(@RequestBody LoginOut loginOut) {
        return "";
    }

    @PreAuthorize("hasAuthority('user:list')")
    @PostMapping("/userList")
    public String userList() {
        return "[]";
    }

    @PreAuthorize("hasAuthority('user:update')")
    @PostMapping("/userUpdate")
    public String userUpdate() {
        return "true";
    }

    @PostMapping("/getUserName")
    public String getUserName(HttpServletRequest request) {
        Object userName = request.getAttribute("userName");
        if(userName != null) {
            return userName.toString();
        }
        return "";
    }

}
