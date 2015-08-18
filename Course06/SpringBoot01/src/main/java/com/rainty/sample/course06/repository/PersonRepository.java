package com.rainty.sample.course06.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rainty.sample.course06.Person;

@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends MongoRepository<Person, String> {

	public Person findByFirstName(@Param("firstName") String firstName);

	public List<Person> findByLastName(@Param("lastName") String lastName);

}
