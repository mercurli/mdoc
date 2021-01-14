package com.mer.mdoc.modules.system.controller;

import com.mer.mdoc.core.vo.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxc
 * @date 2021/1/13
 * @version: 1.0.0
 * @title
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/api/hello")
    public String apiHello() {
        return "apiHello";
    }

    @RequestMapping("/auth/logout")
    public String logout() {
        return "logout";
    }

    @RequestMapping("/auth/2step-code")
    public String auth() {
        return "/auth/2step-code";
    }

    @RequestMapping("/auth/login")
    public Result<Object> login(String username, String password) {
        System.out.println(password);
        Map<String, Object> data = new HashMap<>();
        data.put("token", "123");
        return Result.data(data);
    }

}
