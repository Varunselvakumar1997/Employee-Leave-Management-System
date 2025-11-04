package tech.employeeLeaveManagementSystem.employee_leave_management_system.service;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.dto.LeaveApprovalDto;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.dto.LeaveRequestDto;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.entity.Employee;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.entity.LeaveRequest;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.enums.LeaveStatus;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.enums.LeaveType;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.repository.EmployeeRepo;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.repository.LeaveRequestRepo;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LeaveRequestService {

    private final LeaveRequestRepo leaveRequestRepo;

    private final EmployeeRepo employeeRepo;

    // Method for leave request
    @Transactional
    public ResponseEntity<?> submitLeaveRequest(LeaveRequestDto leaveRequestDto) {

        Optional<Employee> employeeList = employeeRepo.findById(leaveRequestDto.getEmployeeId());

        if(employeeList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with this id " +leaveRequestDto.getEmployeeId()+ " not found");
        }

        Employee employee = employeeList.get();

        long daysRequested = ChronoUnit.DAYS.between(leaveRequestDto.getStartDate(),leaveRequestDto.getEndDate());

        if (leaveRequestDto.getLeaveType() == LeaveType.PRIVILEGED && employee.getLeaveBalance().doubleValue() < daysRequested) {
            return ResponseEntity.badRequest().body("Your leave balance is insufficient, So you can't able to submit the request");
        }

        LeaveRequest newLeaveRequest = new LeaveRequest();
        newLeaveRequest.setEmployee(employee);
        newLeaveRequest.setStartDate(leaveRequestDto.getStartDate());
        newLeaveRequest.setEndDate(leaveRequestDto.getEndDate());
        newLeaveRequest.setType(leaveRequestDto.getLeaveType());
        newLeaveRequest.setStatus(LeaveStatus.PENDING);

        leaveRequestRepo.save(newLeaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("Leave request submitted successfully");
    }


    // Approve or Reject leave request
    @Transactional
    public ResponseEntity<?> approveOrRejectLeaveRequest(LeaveApprovalDto leaveApprovalDto){

        Optional<LeaveRequest> leaveRequestList = leaveRequestRepo.findById(leaveApprovalDto.getLeaveRequestId());

        if(leaveRequestList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave request not found for the Id " +leaveApprovalDto.getLeaveRequestId());
        }

        LeaveRequest existingRequest = leaveRequestList.get();

        if(existingRequest.getStatus() != LeaveStatus.PENDING) {
            return ResponseEntity.badRequest().body("Only Pending leave requests can be approved or rejected!");
        }

        existingRequest.setStatus(leaveApprovalDto.getLeaveStatus());

        if(leaveApprovalDto.getLeaveStatus() == LeaveStatus.APPROVED && existingRequest.getType() == LeaveType.PRIVILEGED){

            Employee employee = existingRequest.getEmployee();

            long daysRequested = ChronoUnit.DAYS.between(existingRequest.getStartDate(),existingRequest.getEndDate()) + 1;

            BigDecimal updatedBalance = employee.getLeaveBalance().subtract(BigDecimal.valueOf(daysRequested));

            employee.setLeaveBalance(updatedBalance);
            employeeRepo.save(employee);
        }

        leaveRequestRepo.save(existingRequest);

        String message = (leaveApprovalDto.getLeaveStatus() == LeaveStatus.APPROVED)
                ? "Leave request approved successfully!" : "Leave request is rejected!";

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }


}