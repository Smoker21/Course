package com.rainsoft.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rainsoft.demo.domain.Customer;


@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("select c from Customer c where c.name =:name") 
	List<Customer> findByName(String name);
}
