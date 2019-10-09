package com.mer.mdoc.modules.system.controller;

import com.mer.mdoc.core.util.IdUtil;
import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.system.entity.SysRole;
import com.mer.mdoc.modules.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Mercurli
 * @date 2019/10/8
 * @version: 1.0
 * @title
 */
@RestController
@RequestMapping("/role/")
public class RoleContorller {

    @Autowired
    private RoleService roleService;

    @RequestMapping("add")
    public Result addRole(SysRole role) {
        return roleService.addRole(role);
    }

    @RequestMapping("getRoles")
    public Result getRoleList() {
        return roleService.getRoleList();
    }
}
