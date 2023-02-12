package com.greatlearning.employeemgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greatlearning.employeemgmt.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	@Query(value = "select * from employee e where e.emp_first_name like %:keyword%", nativeQuery = true)
	List<Employee> findByKeyword(@Param("keyword") String keyword);
}
