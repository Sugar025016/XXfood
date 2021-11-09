package com.xxxx.springsecuritydemo.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author: RedisInitializer
 * @date: 2021/10/24
 * @description:
 */

public class RedisInitializer extends AbstractHttpSessionApplicationInitializer {

    public RedisInitializer() {
        super(RedisConfig.class);
    }
}
