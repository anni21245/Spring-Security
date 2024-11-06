package net.javalearner.springwithpostgres.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.javalearner.springwithpostgres.dto.EmployeeDto;
import net.javalearner.springwithpostgres.entity.Employee;
import net.javalearner.springwithpostgres.mapper.EmployeeMapper;
import net.javalearner.springwithpostgres.repository.EmployeeRepository;
import net.javalearner.springwithpostgres.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;
    
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        logger.debug("Creating employee: {}", employeeDto);
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto result = EmployeeMapper.mapToEmployeeDto(savedEmployee);
        logger.debug("Employee saved: {}", result);
        return result;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            return EmployeeMapper.mapToEmployeeDto(employee);
        } else {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }
    }
    
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }
    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFirstName(employeeDto.getFirstName());
            existingEmployee.setLastName(employeeDto.getLastName());
            existingEmployee.setEmail(employeeDto.getEmail());
            
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
        } else {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }
    }
    @Override
    public void deleteEmployee(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        
        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteById(employeeId);
        } else {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }
    }

}
