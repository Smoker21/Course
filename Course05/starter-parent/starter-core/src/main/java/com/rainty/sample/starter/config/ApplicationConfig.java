package com.rainty.sample.starter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.rainty.sample.starter.dao")
@Import(H2DatabaseConfig.class)
public class ApplicationConfig {

}
