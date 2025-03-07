package com.ems.ems.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.ems.entity.Employee;

@Repository
public interface  EmpRepository extends JpaRepository<Employee,Integer> {

	List<Employee> findByDepartmentDepartmentName(String departmentName);

}

