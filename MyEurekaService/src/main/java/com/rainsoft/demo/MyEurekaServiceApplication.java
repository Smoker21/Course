package com.rainsoft.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Configuration
@EnableDiscoveryClient
@RestController
public class MyEurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEurekaServiceApplication.class, args);
	}
	
	@RequestMapping(path="/sayHello/{name}")
	@ResponseBody
	public String hello(@PathVariable("name") String name) {
		return "Hello! " + name;
	}
}
