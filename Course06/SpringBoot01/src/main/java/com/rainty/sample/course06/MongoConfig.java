package com.rainty.sample.course06;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig {

	private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);

	@Value("${spring.data.mongodb.host}")
	private String host;

	@Value("${spring.data.mongodb.host}")
	private Integer port;

	/**
	 * Configuration for Mongo Db access
	 *
	 * @return
	 */
	@Bean
	public MongoClient mongo() {
		MongoClient client = null;
		try {
			client = new MongoClient(host, port);
		} catch (final UnknownHostException e) {
			logger.error("error in connect to mongodb", e);
			throw new RuntimeException("error in connect to mongodb", e);
		}
		return client;
	}

}
