package com.greatlearning.employee.management.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.greatlearning.employee.management.entity.Employee;
import com.greatlearning.employee.management.entity.Role;
import com.greatlearning.employee.management.entity.User;
import com.greatlearning.employee.management.repository.EmployeeRepository;
import com.greatlearning.employee.management.repository.RoleRepository;
import com.greatlearning.employee.management.repository.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee employee = null;
		if (result.isPresent()) {
			employee = result.get();
		} else {
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		return employee;
	}

	@Override
	public Employee save(Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}

	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
	}

	@Override
	public List<Employee> sortByFirstNameAsc() {
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

}
