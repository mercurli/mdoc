package com.mer.mdoc.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.system.entity.SysUser;
import com.mer.mdoc.modules.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Mercurli
 * @date 2019/10/4
 * @version: 1.0
 * @title
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return sysUserMapper.selectOne(queryWrapper);
    }

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    public boolean checkUsernameExist(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return sysUserMapper.selectOne(queryWrapper) != null;
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
        if (userInfo.getStatus() == 0) {
            return Result.error("用户"+ userInfo.getUsername() +"已注销");
        }
        return Result.ok();
    }

    public boolean add(SysUser user) {
        return sysUserMapper.insert(user) == 1;
    }


}
