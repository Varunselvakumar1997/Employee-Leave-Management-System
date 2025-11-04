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

        if (employeeRequestSaveDto.getId() != null) {
            Optional<Employee> employeeData = employeeRepo.findById(employeeRequestSaveDto.getId());
            employee = employeeData.orElse(new Employee());
        } else {
            employee = new Employee();
        }

        employee.setName(employeeRequestSaveDto.getName());
        employee.setEmail(employeeRequestSaveDto.getEmail());
        employee.setJoiningDate(employeeRequestSaveDto.getJoiningDate());
        employee.setLeaveBalance(employeeRequestSaveDto.getLeaveBalance());

        employeeRepo.save(employee);
    }



    // Get leave balance of an employee
    public BigDecimal getLeaveBalance(Long employeeId){
        Optional <Employee> employeeData = employeeRepo.findById(employeeId);
        return employeeData.get().getLeaveBalance();
    }

}