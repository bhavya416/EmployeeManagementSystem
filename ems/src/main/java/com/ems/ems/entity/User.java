package com.ems.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="userDetails")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)

	private int userId;

	private String userName;

	private String password;
	
	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
