package com.rainty.sample.course06;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@RestController
public class Application {

	private Set<Person> users;

	public static void main(final String... args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/hello/{name}")
	String sayHi(@PathVariable final String name) {
		return "Say Hi to \"" + name + "\"";
	}

	@RequestMapping(value = "/api/user/", method = RequestMethod.POST)
	ResponseEntity<Set<Person>> addUser(@RequestBody final Person person) {
		if (users == null) {
			this.users = new HashSet<Person>();
		}
		users.add(person);
		return new ResponseEntity<Set<Person>>(users, HttpStatus.OK);
	}

}
