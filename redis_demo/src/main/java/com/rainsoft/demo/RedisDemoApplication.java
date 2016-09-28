package com.rainsoft.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class RedisDemoApplication {

	@Autowired
	private RedisTemplate<String,String> template;
	
	@RequestMapping(path="/api/getObject/{name}", method= RequestMethod.GET )
	@ResponseBody
	public Map<String,String> getObject(@PathVariable(name="name") String myname) {
		Map<String,String> result = new HashMap<>();
		BoundValueOperations<String,String> op = template.boundValueOps(myname);
		String value = op.get();
		result.put(myname, value);
		return result;
	}
	
	@RequestMapping(path="/api/insertObject/{name}", method= RequestMethod.POST)	
	@ResponseBody
	public Map<String,String> insertObject(@PathVariable(name="name") String myname, @RequestBody String myValue) {
		Map<String,String> result = new HashMap<>();
		BoundValueOperations<String,String> op = template.boundValueOps(myname);
		op.set(myValue);
		result.put("test1", "Test Value");
		return result;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(RedisDemoApplication.class, args);
	}
	
	
}
