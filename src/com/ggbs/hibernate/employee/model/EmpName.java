package com.ggbs.hibernate.employee.model;

import javax.persistence.Embeddable;

@Embeddable
public class EmpName {

	private String firstName;
	private String lastName;
	private String surName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	@Override
	public String toString() {
		return "EmpName [firstName=" + firstName + ", lastName=" + lastName + ", surName=" + surName + "]";
	}
	
}
