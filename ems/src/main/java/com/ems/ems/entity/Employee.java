package com.ems.ems.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Employee")
@Data

public class Employee {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int employeeId;
	private String employeeName;
	private String email;
	private double salary;
	
	
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)	
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "department_id",nullable=false )
	private Department department;

}
