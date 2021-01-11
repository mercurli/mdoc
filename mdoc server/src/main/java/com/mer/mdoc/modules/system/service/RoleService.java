package com.mer.mdoc.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mer.mdoc.modules.system.entity.SysRole;
import com.mer.mdoc.modules.system.mapper.SysRoleMapper;
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
    private SysRoleMapper sysRoleMapper;

    public boolean add(SysRole role) {
        return sysRoleMapper.insert(role) > 0;
    }

    public boolean remove(String id) {
        return sysRoleMapper.deleteById(id) > 0;
    }

    public List<SysRole>  getList() {
        return sysRoleMapper.selectList(new QueryWrapper<>());
    }

}
