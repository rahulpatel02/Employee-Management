package com.employeemanagement.controller;

import com.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.service.EmployeeService2;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/employees")
@RequiredArgsConstructor
public class EmployeeController2 {
    
    private final EmployeeService2 employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getEmployee(@RequestParam Integer size, @RequestParam  Integer pageNo){
        return ResponseEntity.ok(employeeService.getEmployee(size,pageNo));
    }
    @PutMapping("/{id}")
   public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id , @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.updateEmployee(id , employeeDTO));
   }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
         return ResponseEntity.noContent().build();
    }
}
