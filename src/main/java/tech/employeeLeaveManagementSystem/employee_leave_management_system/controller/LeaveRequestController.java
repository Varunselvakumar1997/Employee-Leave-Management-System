package tech.employeeLeaveManagementSystem.employee_leave_management_system.controller;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.dto.LeaveApprovalDto;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.dto.LeaveRequestDto;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.service.LeaveRequestService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/leaveRequest")
public class LeaveRequestController{

    private final LeaveRequestService leaveRequestService;

    // To submit leave request
    @PostMapping("/submitLeaveRequest")
    public ResponseEntity<?> submitLeaveRequest(@RequestBody LeaveRequestDto leaveRequestDto){
        return leaveRequestService.submitLeaveRequest(leaveRequestDto);
    }

    // To Approve / reject leave
    @PostMapping("/approveOrRejectLeaveRequest")
    public ResponseEntity<?> approveOrReject(@RequestBody LeaveApprovalDto leaveApprovalDto){
        return leaveRequestService.approveOrRejectLeaveRequest(leaveApprovalDto);
    }


}