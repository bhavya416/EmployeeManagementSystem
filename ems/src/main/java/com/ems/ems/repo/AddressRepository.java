package com.ems.ems.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.ems.entity.Address;

@Repository
public interface  AddressRepository extends JpaRepository<Address,Integer> {


}

