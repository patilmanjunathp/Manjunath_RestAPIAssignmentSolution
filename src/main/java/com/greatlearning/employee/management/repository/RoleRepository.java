package com.greatlearning.employee.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.greatlearning.employee.management.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
