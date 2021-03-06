package com.mer.mdoc.modules.system.controller;

import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.system.entity.SysRole;
import com.mer.mdoc.modules.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Result<Object> add(SysRole role) {
        if (roleService.add(role)) {
            return Result.ok();
        } else {
            return Result.error("创建失败");
        }
    }

    @RequestMapping("remove")
    public Result<Object> remove(String id) {
        if (roleService.remove(id)) {
            return Result.ok();
        } else {
            return Result.error("删除失败");
        }
    }

    @RequestMapping("getList")
    public Result<List<SysRole>> getList() {
        Result<List<SysRole>>result = new Result<>(true);
        result.setData(roleService.getList());
        return result;
    }

}
