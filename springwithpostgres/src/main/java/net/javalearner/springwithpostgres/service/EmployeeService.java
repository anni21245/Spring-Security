package net.javalearner.springwithpostgres.service;

import java.util.List;

import org.springframework.stereotype.Service;

import net.javalearner.springwithpostgres.dto.EmployeeDto;
@Service
public interface EmployeeService {
EmployeeDto createEmployee(EmployeeDto employeeDto);
EmployeeDto getEmployeeById(Long employeeId);
List<EmployeeDto> getAllEmployees();
EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);
void deleteEmployee(Long employeeId);
}

