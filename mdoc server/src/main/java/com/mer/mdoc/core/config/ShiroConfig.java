package com.mer.mdoc.core.config;

import com.mer.mdoc.modules.system.shiro.realm.ShiroRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mercurli
 * @date 2019/9/23
 * @version: V1.0
 * @title
 */
@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilter;
    }

    @Bean
    public DefaultSecurityManager securityManager(ShiroRealm shiroRealm) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(shiroRealm);

        return securityManager;
    }
}
