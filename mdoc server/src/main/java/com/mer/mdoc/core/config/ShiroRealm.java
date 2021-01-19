package com.mer.mdoc.core.config;

import com.mer.mdoc.modules.system.entity.SysUser;
import com.mer.mdoc.modules.system.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mercurli
 * @date 2019/9/23
 * @version: V1.0
 * @title
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("doGetAuthorizationInfo...");
        // SimpleAuthorizationInfo：代表用户角色权限信息
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        return s;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo");
        // 从token里获取身份信息：用户名userName，token代表用户输入的信息
        // principal : 主体的标示，可以有多个，但是需要具有唯一性，常见的有用户名，手机号，邮箱等
        String userName = token.getPrincipal().toString();
        // 根据用户名（唯一标识）从数据库里查到这个用户
        SysUser user = userService.getByUsername(userName);
        // 取出这个用户的密码
        String passwordInDB = user.getPassword();
        //取出这个用户的盐
        String salt = user.getSalt();
        //SimpleAuthenticationInfo ：代表该用户的认证信息。参数为“用户名+密码+盐”
        //this.getName()是获取CachingRealm的名字
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }

}
