package com.ems.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="Address")
@Data
public class Address {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int addressId;
	private String street;
	private String city;
	
	@OneToOne
	@JoinColumn(name="employee_id",nullable=false)	
	private Employee employee;

}
