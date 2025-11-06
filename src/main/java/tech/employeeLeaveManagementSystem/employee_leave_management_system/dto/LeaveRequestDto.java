package tech.employeeLeaveManagementSystem.employee_leave_management_system.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.enums.LeaveType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDto {

    @NotNull(message = "Employee ID is required.")
    private Long employeeId;
    @NotNull(message = "Start date is required.")
    private LocalDate startDate;
    @NotNull(message = "End date is required.")
    private LocalDate endDate;
    @NotNull(message = "Leave type is required.")
    private LeaveType leaveType;

}

