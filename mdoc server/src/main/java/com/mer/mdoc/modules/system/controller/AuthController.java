package com.mer.mdoc.modules.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mer.mdoc.core.util.PasswordUtils;
import com.mer.mdoc.core.util.TokenUtils;
import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.system.entity.SysUser;
import com.mer.mdoc.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wxc
 * @since 0.1.0
 * @date 2021/1/16
 */
@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public Result<Object> login(@RequestBody String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        SysUser userInfo = userService.getUserByUsername(username);
        // 校验用户名
        Result<Object> result = userService.checkUserEnable(userInfo);
        if (!result.isSuccess()) {
            return result;
        }
        if (!userInfo.getId().equals(PasswordUtils.decrypt(userInfo.getPassword(), password, userInfo.getSalt()))) {
            return Result.error("密码错误");
        }
        Map<String, Object> data = new HashMap<>();
        data.put("token", TokenUtils.getToken(username, userInfo.getId(), ""));
        return Result.data(data);
    }

    @RequestMapping("logout")
    public Result<Object> logout() {
        return Result.ok();
    }

    @RequestMapping("register")
    public Result<Object> register(@RequestBody SysUser user) {
        if (userService.checkUsernameExist(user.getUsername())) {
            return Result.error("该用户名已存在");
        }
        String salt = PasswordUtils.genSalt(8);
        user.setPassword(PasswordUtils.encrypt(user.getId(), user.getPassword(), salt));
        user.setName(user.getUsername());
        user.setSalt(salt);
        if (!userService.add(user)) {
            return Result.error("注册失败");
        }
        return Result.ok();
    }

}
