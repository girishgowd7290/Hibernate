package com.ggbs.hibernate.relation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	private int rollNum;
	private String name;
	private String dept;
	
	// If we use mappedBy and provide the corresponding class's variable name then new class will not be created
	// while fetching Student - Hib will fetch only student class info
	// If you need Collection of laptops as well then we need to specify Fetch Type as EAGER
	@OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
	private List<Laptop> laptop = new ArrayList<Laptop>();
	
	
	public List<Laptop> getLaptop() {
		return laptop;
	}
	public void setLaptop(List<Laptop> laptop) {
		this.laptop = laptop;
	}
	public int getRollNum() {
		return rollNum;
	}
	public void setRollNum(int rollNum) {
		this.rollNum = rollNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "Student [rollNum=" + rollNum + ", name=" + name + ", dept=" + dept + "]";
	}
	
	
}
