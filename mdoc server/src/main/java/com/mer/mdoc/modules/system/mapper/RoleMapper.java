package com.mer.mdoc.modules.system.mapper;

import com.mer.mdoc.modules.system.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Mercurli
 * @date 2019/10/8
 * @version: 1.0
 * @title
 */
@Mapper
public interface RoleMapper {

    int addRole(SysRole role);

    List<SysRole> getRoleList();
}
