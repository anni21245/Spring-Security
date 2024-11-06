package net.javalearner.springwithpostgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javalearner.springwithpostgres.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
