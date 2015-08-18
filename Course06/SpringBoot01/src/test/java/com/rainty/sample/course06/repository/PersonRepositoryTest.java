package com.rainty.sample.course06.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rainty.sample.course06.Application;
import com.rainty.sample.course06.Person;
import com.rainty.sample.course06.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PersonRepositoryTest {

	@Autowired
	PersonRepository repository;

	@Autowired
	PersonService service;

	@Test
	public void testSaveS() {
		repository.save(new Person("Test", "Test", "Who@am.i"));
		final Person p = service.save(new Person("Test", "Test", "Who@am.i"));
		System.out.println(repository.count());
		System.out.println(p);
	}
}
