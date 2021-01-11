package com.mer.mdoc.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxc
 * @date 2021/1/11
 * @version: 1.0.0
 * @title
 */
@MapperScan("com.mer.mdoc.modules.*.mapper")
@Configuration
public class MyBatisConfig {
}
