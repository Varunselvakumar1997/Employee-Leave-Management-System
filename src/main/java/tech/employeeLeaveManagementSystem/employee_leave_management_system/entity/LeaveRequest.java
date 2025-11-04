package tech.employeeLeaveManagementSystem.employee_leave_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.enums.LeaveStatus;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.enums.LeaveType;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @Enumerated(EnumType.STRING)
    private LeaveType type;

}
