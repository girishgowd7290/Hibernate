package com.ggbs.hibernate.relation.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Laptop {

	@Id
	private int laptopId;
	private String laptopMake;

	@ManyToOne
	private Student student;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public int getLaptopId() {
		return laptopId;
	}
	public void setLaptopId(int laptopId) {
		this.laptopId = laptopId;
	}
	public String getLaptopMake() {
		return laptopMake;
	}
	public void setLaptopMake(String laptopMake) {
		this.laptopMake = laptopMake;
	}
	@Override
	public String toString() {
		return "Laptop [laptopId=" + laptopId + ", laptopMake=" + laptopMake + "]";
	}
	
	
}
