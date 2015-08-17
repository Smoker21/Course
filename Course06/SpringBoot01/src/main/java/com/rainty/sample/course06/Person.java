package com.rainty.sample.course06;

import org.springframework.data.annotation.Id;

public class Person {

	@Id
	private String id;

	private String firstName;
	private String lastName;

	public Person() {
	}

	public Person(final String firstName, final String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Person [id=%s, firstName=%s, lastName=%s]", id, firstName, lastName);
	}

}
