package com.ems.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ems.ems.entity.Department;
import com.ems.ems.entity.Employee;
import com.ems.ems.repo.DeptRepository;
import com.ems.ems.repo.EmpRepository;


@Service
public class EmsService {
	
	
	@Autowired
	EmpRepository empRepo;
	
	@Autowired
	DeptRepository deptRepo;
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public Employee hireEmployee(Employee emp, int departmentID) {
		Department department = deptRepo.findById(departmentID).orElseThrow(()->new RuntimeException("No such Dept ID"));
		
		emp.setDepartment(department);
		Employee savedEmp = empRepo.save(emp);
		return savedEmp;
	}
	 @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
	public Employee transfer(int empId, int deptId) {
		
		Employee Emp = empRepo.findById(empId).orElseThrow(()->new RuntimeException("No such EMPID exists"));
		Department department = deptRepo.findById(deptId).orElseThrow(()->new RuntimeException("No such Dept ID"));
		Emp.setDepartment(department);
		return empRepo.save(Emp);
		
		
	}
	 
	 //save 2 entity crating dept and save 
	 
		@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
		public List<Employee> getAllEmployees() {
			return empRepo.findAll();
		}

	public List<Employee> getEmpByDeptName(Department dpt) {
		
		List<Employee> emplist = empRepo.findByDepartmentDepartmentName(dpt.getDepartmentName());
		return emplist;
   }
	public Department addDepartment(Department department) {
		return deptRepo.save(department);
	}
	
	public void deleteDepartmentById(int id) {
		deptRepo.deleteById(id);
	}


	
	
	

}
