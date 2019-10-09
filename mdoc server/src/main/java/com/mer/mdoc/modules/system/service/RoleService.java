package com.mer.mdoc.modules.system.service;

import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.system.entity.SysRole;
import com.mer.mdoc.modules.system.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mercurli
 * @date 2019/10/8
 * @version: 1.0
 * @title
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Result addRole(SysRole role) {
        int i = roleMapper.addRole(role);
        if (i == 1) {
            return Result.ok();
        } else {
            return Result.error("创建失败");
        }
    }

    public Result getRoleList() {
        Result result = new Result();
        result.setSuccess(true);
        List<SysRole> roles = roleMapper.getRoleList();
        result.setData(roles);
        return result;
    }
}
