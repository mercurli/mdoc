package com.mer.mdoc.modules.system.controller;

import com.mer.mdoc.core.util.PasswordUtil;
import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.system.entity.SysUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mercurli
 * @date 2019/10/4
 * @version: 1.0
 * @title
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @RequestMapping("add")
    public Result<Object> addUser(SysUser user) {
        String salt = PasswordUtil.genSalt(8);
        user.setCreateTime(new Date());
        user.setSalt(salt);
        return Result.ok();
    }

    @RequestMapping("info")
    public Map<String, Object> info() {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        data.put("result", result);
        result.put("id", "1");
        result.put("username", "admin");
        result.put("password", "");
        result.put("name", "Mer");
        result.put("telephone", "");
        result.put("status", 1);
        result.put("createTime", new Date());
        result.put("roleId", "admin");
        result.put("role", null);
        return data;
    }

}
