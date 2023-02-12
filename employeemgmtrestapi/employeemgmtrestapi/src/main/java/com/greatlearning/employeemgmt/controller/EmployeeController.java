package com.greatlearning.employeemgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeemgmt.model.Employee;
import com.greatlearning.employeemgmt.model.Role;
import com.greatlearning.employeemgmt.model.User;
import com.greatlearning.employeemgmt.repository.EmployeeRepository;
import com.greatlearning.employeemgmt.repository.RoleRepository;
import com.greatlearning.employeemgmt.repository.UserRepository;
import com.greatlearning.employeemgmt.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	// api to add roles to db dynamically
	@PostMapping("/addRole")
	public String addRole(@RequestBody Role role) {
		roleRepository.save(role);
		return "added role successfully";
	}

	// api to insert user
	@PostMapping("/addUser")
	public void addUser(@RequestBody User user) {
		userRepository.save(user);
	}

	// api to insert a created employee to database
	@PostMapping("/addEmployee")
	public String addEmployee(Employee emp) {
		service.saveOrUpdate(emp);
		return "employee added";
	}

	// api to fetch all the employees form the database
	@GetMapping("/list")
	public List<Employee> getListOfEmployees() {
		List<Employee> lemp = service.getAllEmployee();
		return lemp;
	}

	// api to find an employee by id
	@GetMapping("/{id}")
	public Employee getEmployeeByid(@PathVariable(value = "id") int id) {
		return service.getEmployeeById(id);
	}

	// api to update an employee
	@PutMapping("/update")
	public Employee update(@RequestBody Employee emp) {
		Employee exsistingEmployee = service.getEmployeeById(emp.getId());
		exsistingEmployee.setFirstName(emp.getFirstName());
		exsistingEmployee.setLastName(emp.getLastName());
		exsistingEmployee.setEmail(emp.getEmail());

		service.saveOrUpdate(exsistingEmployee);
		return exsistingEmployee;
	}

	// api to delete an employee by id
	@PostMapping("/delete/{id}")
	public String delelteEmployeeById(@PathVariable(value = "id") int id) {
		service.deleteEmployeeById(id);
		return "employee deleted";
	}

	// api to search an employee by firstname
	@GetMapping("/search/{firstName}")
	public List<Employee> search(@PathVariable(value = "firstName") String firstName) {
		List<Employee> listEmp = employeeRepository.findByKeyword(firstName);
		return listEmp;
	}

}
