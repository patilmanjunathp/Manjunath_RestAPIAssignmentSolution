package com.greatlearning.employee.management.controller;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.IntNode;
import com.greatlearning.employee.management.entity.Employee;
import com.greatlearning.employee.management.entity.Role;
import com.greatlearning.employee.management.entity.User;
import com.greatlearning.employee.management.service.EmployeeService;

import net.bytebuddy.implementation.attribute.AnnotationAppender.Target.OnType;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);
	}

	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return employeeService.saveRole(role);
	}

	@GetMapping("/employees")
	public List<Employee> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee employee = employeeService.findById(employeeId);
		if (employee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		return employee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		employeeService.save(employee);
		return employee;
	}

	@PutMapping("/employees/{employeeId}")
	public Employee updateEmployee(@RequestBody Employee employee , @PathVariable int employeeId) {
		Employee employeeDetails=employeeService.findById(employeeId);
		if (employeeDetails == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		employeeDetails.setFirstName(employee.getFirstName());
		employeeDetails.setLastName(employee.getLastName());
		employeeDetails.setEmail(employee.getEmail());
		
		Employee updatedEmployee=employeeService.save(employeeDetails);
		return updatedEmployee;
	}

	@DeleteMapping("/employees/{employeeId}")

	public String deleteEmployee(@PathVariable int employeeId) {
		Employee employee = employeeService.findById(employeeId);
		if (employee == null) {
			throw new RuntimeException("Employee id not found : " + employeeId);
		}
		employeeService.deleteById(employeeId);
		return "Deleted employee id - " + employeeId;
	}

	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeService.searchByFirstName(firstName);
	}

	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName() {
		return employeeService.sortByFirstNameAsc();
	}

}
