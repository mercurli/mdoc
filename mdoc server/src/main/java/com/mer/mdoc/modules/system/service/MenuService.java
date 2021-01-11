package com.mer.mdoc.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mer.mdoc.modules.system.entity.SysMenu;
import com.mer.mdoc.modules.system.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mercurli
 * @date 2019/10/15
 * @version: 1.0
 * @title
 */
@Service
public class MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public boolean add(SysMenu menu) {
        return sysMenuMapper.insert(menu) > 0;
    }

    public boolean remove(String id) {
        return sysMenuMapper.deleteById(id) > 0;
    }

    public List<SysMenu> getList() {

        return sysMenuMapper.selectList(new QueryWrapper<>());
    }
}
