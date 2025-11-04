package tech.employeeLeaveManagementSystem.employee_leave_management_system.dto;

import lombok.*;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.enums.LeaveType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDto {

    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType leaveType;

}

