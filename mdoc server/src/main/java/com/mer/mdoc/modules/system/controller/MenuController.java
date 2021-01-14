package com.mer.mdoc.modules.system.controller;

import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.system.entity.SysMenu;
import com.mer.mdoc.modules.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mercurli
 * @date 2019/10/15
 * @version: 1.0
 * @title
 */
@RestController
@RequestMapping("/menu/")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("add")
    public Result<Object> add(SysMenu menu) {
        if (menuService.add(menu)) {
            return Result.ok();
        } else {
            return Result.error("创建失败");
        }
    }

    @RequestMapping("remove")
    public Result<Object> remove(String id) {
        if (menuService.remove(id)) {
            return Result.ok();
        } else {
            return Result.error("删除失败");
        }
    }

    @RequestMapping("getList")
    public Result<List<SysMenu>> getList() {
        Result<List<SysMenu>> result = new Result<>(true);
        result.setData(menuService.getList());
        return result;
    }
}
