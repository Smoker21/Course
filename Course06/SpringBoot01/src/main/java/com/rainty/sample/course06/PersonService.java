package com.rainty.sample.course06;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rainty.sample.course06.repository.PersonRepository;

@Component
@RestController
public class PersonService {

	@Value("${local.server.port}")
	int port;

	@Value("${local.server.host}")
	String host;

	@Autowired
	PersonRepository repository;

	public Person save(final Person p) {
		return repository.save(p);
	}

	@RequestMapping("/hello/{name}")
	String sayHi(@PathVariable final String name) {
		repository.save(new Person("Lance", "Chen"));
		repository.save(new Person("Sakura", "Chen"));
		repository.save(new Person("Jane", "Chen"));
		repository.save(new Person("Hunter", "Chen"));
		return "Say Hi to \"" + name + "\" at" + host + ":" + port;
	}

	@RequestMapping(value = "/api/user/", method = RequestMethod.POST)
	ResponseEntity<List<Person>> addUser(@RequestBody final Person person) {
		repository.save(person);
		final List<Person> persons = repository.findAll();
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}
}
