package com.rainsoft.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class MyFirstAppApplication {
	
	@RequestMapping(path="/hello/{name}",method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String sayHello(@PathVariable(name="name") String name) {
		return "Hello " + name; 
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MyFirstAppApplication.class, args);
	}
}
