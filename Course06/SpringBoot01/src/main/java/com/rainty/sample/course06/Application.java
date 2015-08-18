package com.rainty.sample.course06;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClient;

@Configuration
@EnableAutoConfiguration
@RestController
@ComponentScan
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(final String... args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Configuration for Mongo Db access
	 *
	 * @return
	 */
	@Bean
	public MongoClient mongo() {
		MongoClient client = null;
		try {
			client = new MongoClient("192.168.99.100", 27017);
		} catch (final UnknownHostException e) {
			logger.error("error in connect to mongodb", e);
			throw new RuntimeException("error in connect to mongodb", e);
		}
		return client;
	}

}
