package com.employeemanagement.service;

import com.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.exception.ResourceNotFoundException;
import com.employeemanagement.mapper.EmployeeMapper;
import com.employeemanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.info("Creating employee with email: {}", employeeDTO.getEmail());
        
        if (employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
            log.error("Employee with email {} already exists", employeeDTO.getEmail());
            throw new IllegalArgumentException("Employee with this email already exists");
        }
        
        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee.setId(null); // Ensure ID is null for new entity
        Employee savedEmployee = employeeRepository.save(employee);
        
        log.info("Employee created successfully with id: {}", savedEmployee.getId());
        return employeeMapper.toDTO(savedEmployee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        log.info("Fetching employee with id: {}", id);
        
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id: {}", id);
                    return new ResourceNotFoundException("Employee not found with id: " + id);
                });
        
        return employeeMapper.toDTO(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with id: {}", id);
        
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id: {}", id);
                    return new ResourceNotFoundException("Employee not found with id: " + id);
                });
        
        // Check if new email is already in use by another employee
        if (employeeDTO.getEmail() != null && 
            !employeeDTO.getEmail().equals(employee.getEmail()) &&
            employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
            log.error("Email {} is already in use", employeeDTO.getEmail());
            throw new IllegalArgumentException("Email is already in use");
        }
        
        if (employeeDTO.getName() != null) {
            employee.setName(employeeDTO.getName());
        }
        if (employeeDTO.getEmail() != null) {
            employee.setEmail(employeeDTO.getEmail());
        }
        if (employeeDTO.getDepartment() != null) {
            employee.setDepartment(employeeDTO.getDepartment());
        }
        if (employeeDTO.getSalary() != null) {
            employee.setSalary(employeeDTO.getSalary());
        }
        if (employeeDTO.getStatus() != null) {
            employee.setStatus(employeeDTO.getStatus());
        }
        
        Employee updatedEmployee = employeeRepository.save(employee);
        log.info("Employee updated successfully with id: {}", id);
        
        return employeeMapper.toDTO(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
        
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id: {}", id);
                    return new ResourceNotFoundException("Employee not found with id: " + id);
                });
        
        employeeRepository.delete(employee);
        log.info("Employee deleted successfully with id: {}", id);
    }

}

