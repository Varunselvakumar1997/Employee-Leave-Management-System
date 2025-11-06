package tech.employeeLeaveManagementSystem.employee_leave_management_system.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSaveRequestDto {

    private Long id;
    @NotBlank(message = "Employee name is required.")
    private String name;
    @NotBlank(message = "Email address is required.")
    private String email;
    @NotNull(message = "Joining date is required.")
    private LocalDate joiningDate;
    @DecimalMax(value = "30.0", message = "Leave balance cannot exceed 30 days.")
    private BigDecimal leaveBalance;
}
