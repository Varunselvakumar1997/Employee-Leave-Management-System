package tech.employeeLeaveManagementSystem.employee_leave_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveEvent {

    private String eventType;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int totalDays;
    private ZonedDateTime approvedAt;

}
