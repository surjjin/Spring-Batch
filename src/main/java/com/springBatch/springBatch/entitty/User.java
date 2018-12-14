package com.springBatch.springBatch.entitty;

import javax.persistence.Entity;
import javax.persistence.Id;



 @Entity
public class User {
	 
	 @Id
	 private  int id;
	 private String first_name;
	 private String department_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public User(int id, String first_name, String department_name) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.department_name = department_name;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", department_name=" + department_name + "]";
	}
	 
	 
}
