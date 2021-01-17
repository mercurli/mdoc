package com.mer.mdoc.modules.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
