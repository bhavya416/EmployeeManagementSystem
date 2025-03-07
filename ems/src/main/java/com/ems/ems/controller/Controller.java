package com.ems.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.ems.entity.Department;
import com.ems.ems.entity.Employee;
import com.ems.ems.service.EmsService;


@RestController
@RequestMapping("/emp")
public class Controller {
	
	@Autowired
	EmsService service;
	
	@GetMapping("/test")
	public String  testMe() {
		return "I am inside controller";

	}
	
	@PostMapping("/{deptname}")
	public ResponseEntity<Department> addDepartment(@PathVariable String deptname) {
		Department d = new Department();
		d.setDepartmentName(deptname);
		return ResponseEntity.ok(service.addDepartment(d));

	}
	
	@PostMapping("/hireemp/{deptID}")
	public ResponseEntity<Employee> hireEmployee(@RequestBody Employee emp,@PathVariable int departmentID) {
		return ResponseEntity.ok(service.hireEmployee(emp,departmentID));

	}
	
	@PutMapping("/transferemp/{empid}/{deptid}")
	public ResponseEntity<Employee> getEmpByDepartment(@PathVariable int empId,@PathVariable int deptId) {
		return ResponseEntity.ok(service.transfer(empId,deptId));
	
	}
	
	@GetMapping("/getEmployeeByDept/{dpt}")
	public ResponseEntity<List<Employee>> getEmpByDepartmentName(@PathVariable Department dpt) {
		return ResponseEntity.ok(service.getEmpByDeptName(dpt));
	
	}
	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
	
	return ResponseEntity.ok(service.getAllEmployees());
	}
	
	@DeleteMapping("/{id}")
	public void deleteDepartmentById(@PathVariable int id) {
		service.deleteDepartmentById(id);
	}
	
	
	


}
