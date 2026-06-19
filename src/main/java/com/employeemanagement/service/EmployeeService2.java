package com.employeemanagement.service;

import com.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.exception.ResourceNotFoundException;
import com.employeemanagement.mapper.EmployeeMapper;
import com.employeemanagement.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService2 {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDTO> getAllEmployee() {
        log.info("Fetching all employees");
        return employeeRepository.findAll().stream().map(employeeMapper::toDTO).toList();

    }

    public EmployeeDTO getEmployeeById(Long Id) {
        log.info("Fetching employee with id: {}", Id);
        Employee employee = employeeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + Id));
        return employeeMapper.toDTO(employee);
    }

    public EmployeeDTO updateEmployee(Long Id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with id: {}", Id);
        Employee employee = employeeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + Id));

        if (employeeDTO.getEmail() != null &&
                !employeeDTO.getEmail().equals(employee.getEmail()) &&
                employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use :" + employee.getEmail());
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
        Employee save = employeeRepository.save(employee);
        return employeeMapper.toDTO(save);

    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.findById(id).isPresent())
            throw new ResourceNotFoundException("Employee not found with id:" + id);
        employeeRepository.deleteById(id);

    }
    public List<EmployeeDTO> getEmployee(Integer size, Integer pageNo){
        log.info("Fetching employees with pagination - Page: {}, Size: {}", pageNo, size);
        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(Sort.Direction.DESC, "name"));
        return employeeRepository.findAll(pageable).map(employeeMapper::toDTO).toList();
    }

}
