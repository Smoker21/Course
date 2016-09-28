package com.rainty.sample.course06;

import org.springframework.data.annotation.Id;

public class Person {

	@Id
	private String id;

	private String firstName;
	private String lastName;
	private String emailAddress;

	public Person() {
	}

	public Person(final String firstName, final String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(final String firstName, final String lastName, final String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = email;
	}

	public String getId() {
		return id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
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
		return String.format("Person [id=%s, firstName=%s, lastName=%s, emailAddress=%s]", id, firstName, lastName, emailAddress);
	}

}
