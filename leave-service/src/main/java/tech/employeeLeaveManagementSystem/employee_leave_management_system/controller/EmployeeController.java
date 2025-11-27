package tech.employeeLeaveManagementSystem.employee_leave_management_system.controller;


import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.dto.EmployeeSaveRequestDto;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.service.EmployeeService;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/saveEmployeeDetails")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeSaveRequestDto employeeSaveRequestDto){
        try {
            employeeService.saveEmployee(employeeSaveRequestDto);
            return ResponseEntity.ok("Employee saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving employee : " +e.getMessage());
        }

    }

    @GetMapping("/leaveBalance/{employeeId}")
    public ResponseEntity<?> getLeaveBalance(@PathVariable Long employeeId) {
        try {
            BigDecimal leaveBalance = employeeService.getLeaveBalance(employeeId);
            return ResponseEntity.ok(leaveBalance);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


}
