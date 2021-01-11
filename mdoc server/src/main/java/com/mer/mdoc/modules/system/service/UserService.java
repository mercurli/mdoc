package com.mer.mdoc.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.system.entity.SysUser;
import com.mer.mdoc.modules.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mercurli
 * @date 2019/10/4
 * @version: 1.0
 * @title
 */
@Service
public class UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    public SysUser getUserByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return sysUserMapper.selectOne(queryWrapper);
    }

    /**
     * 检查用户是否可用
     * @param userInfo
     * @return
     */
    public Result<Object> checkUserEnable(SysUser userInfo) {
        if (userInfo == null) {
            return Result.error("该用户不存在");
        }
        if (userInfo.getStatus() == 1) {
            return Result.error("用户"+ userInfo.getUsername() +"已注销");
        }
        return Result.ok();
    }
}
