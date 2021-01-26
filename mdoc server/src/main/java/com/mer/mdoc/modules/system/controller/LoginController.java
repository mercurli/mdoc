package com.mer.mdoc.modules.system.controller;

import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.system.entity.SysUser;
import com.mer.mdoc.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mercurli
 * @date 2019/10/4
 * @version: 1.0
 * @title
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result<Object> login(String username, String password) {
        SysUser userInfo = userService.loadUserByUsername(username);
        Result<Object> result = userService.checkUserEnable(userInfo);
        if (!result.isSuccess()) {
            return result;
        }
        return Result.ok();
    }
}
