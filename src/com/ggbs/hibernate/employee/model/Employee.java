package com.ggbs.hibernate.employee.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="Employee")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Employee {

	@Id
	private Long empId;
	private EmpName empName;
	private String designation;
	
	// Getter & Setters
	
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	
	public EmpName getEmpName() {
		return empName;
	}
	public void setEmpName(EmpName empName) {
		this.empName = empName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "User [empId=" + empId + ", empName=" + empName + ", designation=" + designation + "]";
	}
	
	

}
