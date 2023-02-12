package com.greatlearning.employeemgmt.service;

import java.util.List;

import com.greatlearning.employeemgmt.model.Employee;

public interface EmployeeService {
	public Employee createEmp(Employee emp);
	public List<Employee> getAllEmployee();
	public void saveOrUpdate(Employee emp);
	public void deleteEmployeeById(int Id);
	public Employee getEmployeeById(int Id);
}
