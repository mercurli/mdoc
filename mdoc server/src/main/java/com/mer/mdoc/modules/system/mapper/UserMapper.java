package com.mer.mdoc.modules.system.mapper;

import com.mer.mdoc.modules.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Mercurli
 * @date 2019/10/4
 * @version: 1.0
 * @title
 */
@Mapper
public interface UserMapper {

    SysUser getUser(String username);
}
