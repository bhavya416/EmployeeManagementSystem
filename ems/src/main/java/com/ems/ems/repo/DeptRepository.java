package com.ems.ems.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.ems.entity.Department;

@Repository
public interface  DeptRepository extends JpaRepository<Department,Integer> {



}

