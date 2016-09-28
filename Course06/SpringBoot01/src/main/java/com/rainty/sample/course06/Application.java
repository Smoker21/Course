package com.rainty.sample.course06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

@PropertySource("classpath:application.properties")
@Configuration
@EnableAutoConfiguration
@RestController
@ComponentScan(excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = { Configuration.class }) })
@Import(MongoConfig.class)
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(final String... args) {
		SpringApplication.run(Application.class, args);
	}

}
