package tech.employeeLeaveManagementSystem.employee_leave_management_system.dto;

import lombok.*;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.enums.LeaveStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApprovalDto {

    private long leaveRequestId;
    private LeaveStatus leaveStatus;

}
