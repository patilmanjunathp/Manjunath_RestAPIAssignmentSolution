package com.greatlearning.employee.management.service;

import java.util.List;

import com.greatlearning.employee.management.entity.Employee;
import com.greatlearning.employee.management.entity.Role;
import com.greatlearning.employee.management.entity.User;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee findById(int id);

	public Employee save(Employee employee);

	public void deleteById(int id);

	public List<Employee> searchByFirstName(String firstName);

	public List<Employee> sortByFirstNameAsc();

	public User saveUser(User user);

	public Role saveRole(Role role);

}
