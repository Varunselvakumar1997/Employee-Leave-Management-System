package tech.employeeLeaveManagementSystem.employee_leave_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private LocalDate joiningDate;
    private BigDecimal leaveBalance;

}

