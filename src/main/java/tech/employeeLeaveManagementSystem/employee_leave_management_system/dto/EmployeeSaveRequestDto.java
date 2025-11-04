package tech.employeeLeaveManagementSystem.employee_leave_management_system.dto;

import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSaveRequestDto {

    private Long id;
    private String name;
    private String email;
    private LocalDate joiningDate;
    private BigDecimal leaveBalance;
}
