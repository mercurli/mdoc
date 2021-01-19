package com.mer.mdoc.modules.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.mer.mdoc.core.util.PasswordUtils;
import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.system.entity.SysUser;
import com.mer.mdoc.modules.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxc
 * @date 2021/1/16
 * @since 0.1.0
 */
@Slf4j
@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param requestJson
     * @return
     */
    @RequestMapping("login")
    public Result<Object> login(@RequestBody JSONObject requestJson) {
        String username = requestJson.getString("username");
        String password = requestJson.getString("password");
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        currentUser.login(token);
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        log.info("token: {}", token);
        return Result.data(data);
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("logout")
    public Result<Object> logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return Result.ok();
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @RequestMapping("register")
    public Result<Object> register(@RequestBody SysUser user) {
        if (userService.checkUsernameExist(user.getUsername())) {
            return Result.error("该用户名已存在");
        }
        String salt = PasswordUtils.genSalt(8);
        System.out.println("加密前的密码：" + user.getPassword());
        // 加密次数
        int times = 2;
        String encodedPassword = new SimpleHash("md5", user.getPassword(), salt, times).toString();
        System.out.println("加密后的密码：" + encodedPassword);
        user.setPassword(encodedPassword)
                .setName(user.getUsername())
                .setSalt(salt);
        if (!userService.add(user)) {
            return Result.error("注册失败");
        }
        return Result.ok();
    }

}
