package com.greatlearning.employee.management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.greatlearning.employee.management.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByFirstNameContainsAllIgnoreCase(String firstName);

	List<Employee> findAllByOrderByFirstNameAsc();

}
