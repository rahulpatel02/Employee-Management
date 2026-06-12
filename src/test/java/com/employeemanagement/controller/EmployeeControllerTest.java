package com.employeemanagement.controller;

import com.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.exception.ResourceNotFoundException;
import com.employeemanagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for EmployeeController.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Controller Tests")
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeDTO employeeDTO;
    private EmployeeDTO savedEmployeeDTO;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
        employeeDTO = EmployeeDTO.builder()
                .name("John Smith")
                .email("john.smith@company.com")
                .department("Engineering")
                .salary(new BigDecimal("85000.00"))
                .status("ACTIVE")
                .build();

        savedEmployeeDTO = EmployeeDTO.builder()
                .id(1L)
                .name("John Smith")
                .email("john.smith@company.com")
                .department("Engineering")
                .salary(new BigDecimal("85000.00"))
                .status("ACTIVE")
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    @Test
    @DisplayName("Should create employee successfully")
    void testCreateEmployee_Success() {
        when(employeeService.createEmployee(any(EmployeeDTO.class)))
                .thenReturn(savedEmployeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.createEmployee(employeeDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        verify(employeeService, times(1)).createEmployee(any(EmployeeDTO.class));
    }

    @Test
    @DisplayName("Should return 201 status code on creation")
    void testCreateEmployee_Status() {
        when(employeeService.createEmployee(any(EmployeeDTO.class)))
                .thenReturn(savedEmployeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.createEmployee(employeeDTO);

        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    @DisplayName("Should get all employees")
    void testGetAllEmployees_Success() {
        when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(savedEmployeeDTO));

        ResponseEntity<List<EmployeeDTO>> response = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    @DisplayName("Should get employee by id")
    void testGetEmployeeById_Success() {
        when(employeeService.getEmployeeById(1L))
                .thenReturn(savedEmployeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.getEmployeeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    @DisplayName("Should update employee")
    void testUpdateEmployee_Success() {
        when(employeeService.updateEmployee(eq(1L), any(EmployeeDTO.class)))
                .thenReturn(savedEmployeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.updateEmployee(1L, employeeDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(employeeService, times(1)).updateEmployee(eq(1L), any(EmployeeDTO.class));
    }

    @Test
    @DisplayName("Should delete employee")
    void testDeleteEmployee_Success() {
        doNothing().when(employeeService).deleteEmployee(1L);

        ResponseEntity<Void> response = employeeController.deleteEmployee(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(1L);
    }

}

