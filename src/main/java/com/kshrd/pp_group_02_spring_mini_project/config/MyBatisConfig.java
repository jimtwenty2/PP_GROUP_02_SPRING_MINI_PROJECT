package com.kshrd.pp_group_02_spring_mini_project.config;

import com.kshrd.pp_group_02_spring_mini_project.typehandler.UuidTypeHandler;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> {
            configuration.getTypeHandlerRegistry().register(UuidTypeHandler.class);
        };
    }
}