package tech.employeeLeaveManagementSystem.employee_leave_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {

    private String accessToken;
    private String tokenType = "Bearer";
    private String userId;
    private List<String> roles;
}
