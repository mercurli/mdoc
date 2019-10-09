package com.mer.mdoc.modules.system.controller;

import com.mer.mdoc.core.util.PasswordUtil;
import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.system.entity.SysUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Mercurli
 * @date 2019/10/4
 * @version: 1.0
 * @title
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @RequestMapping("/add")
    public Result addUser(SysUser user) {
        String salt = PasswordUtil.genSalt(8);
        user.setCreateTime(new Date());
        user.setSalt(salt);

        return Result.ok();
    }
}
