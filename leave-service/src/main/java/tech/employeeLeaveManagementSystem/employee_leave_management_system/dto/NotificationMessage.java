package tech.employeeLeaveManagementSystem.employee_leave_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {

    private String type;
    private String message;
    private Instant timestamp;
}
