package tech.employeeLeaveManagementSystem.employee_leave_management_system.service;


import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.dto.EmployeeSaveRequestDto;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.entity.Employee;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.repository.EmployeeRepo;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.repository.LeaveRequestRepo;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    // To create employee details
    @Transactional
    public void saveEmployee(EmployeeSaveRequestDto employeeRequestSaveDto) {

        Employee employee;
        if (employeeRequestSaveDto.getEmail() != null) {
            Optional<Employee> existingEmployee = employeeRepo.findByEmail(employeeRequestSaveDto.getEmail());
            employee = existingEmployee.orElse(new Employee());
        } else {
            employee = new Employee();
        }

        employee.setName(employeeRequestSaveDto.getName());
        employee.setEmail(employeeRequestSaveDto.getEmail());
        employee.setJoiningDate(employeeRequestSaveDto.getJoiningDate());

        BigDecimal newLeaveBalance = employeeRequestSaveDto.getLeaveBalance();
        if (employee.getLeaveBalance() != null) {
            newLeaveBalance = employee.getLeaveBalance().add(newLeaveBalance);
        }
        BigDecimal maxBalance = BigDecimal.valueOf(30);
        if (newLeaveBalance.compareTo(maxBalance) > 0) {
            newLeaveBalance = maxBalance;
        }
        employee.setLeaveBalance(newLeaveBalance);

        employeeRepo.save(employee);
    }



    // Get leave balance of an employee
    public BigDecimal getLeaveBalance(Long employeeId){
        Optional <Employee> employeeData = employeeRepo.findById(employeeId);
        return employeeData.get().getLeaveBalance();
    }

}