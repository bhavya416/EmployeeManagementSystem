package com.ems.ems.service;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ems.ems.entity.Department;
import com.ems.ems.repo.DeptRepository;


@ExtendWith(MockitoExtension.class)
public class EmsServiceTest {
	
	@Mock
	DeptRepository deptRepo;
	
	@InjectMocks
	EmsService emsService;
	
	@BeforeAll
	public  static void init() {
		
		System.out.println("this is executed before all the methods only once");
	}
	
	@BeforeEach
	public  void settingUp() {		
		System.out.println("this is executed before each method ");
	}
	
	
	@Test
	public void addDepartmentShouldAddProductSuccessfully() {
		Department department = new Department();
		department.setDepartmentId(1);
		department.setDepartmentName("Engineering");
		Mockito.when(deptRepo.save(department)).thenReturn(department);
		Department saveddepartment=emsService.addDepartment(department);
		
		Assertions.assertEquals(department.getDepartmentId(), saveddepartment.getDepartmentId());
		
	}
	
	@Test
	public void deleteDepartmentTest() {
		Mockito.doNothing().when(deptRepo).deleteById(1);
		emsService.deleteDepartmentById(1);
		Mockito.verify(deptRepo, times(1)).deleteById(1);
		
	}
	
	@AfterEach
	public  static void cleaningup() {
		
		System.out.println("this is executed after each method");
	}
	
	@AfterAll
	public  static void destroy() {		
		System.out.println("this is executed after all the methods only once");
	}

}
