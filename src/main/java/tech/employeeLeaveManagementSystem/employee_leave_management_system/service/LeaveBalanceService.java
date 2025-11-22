package tech.employeeLeaveManagementSystem.employee_leave_management_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.entity.Employee;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.repository.EmployeeRepo;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeaveBalanceService {

    private final EmployeeRepo employeeRepo;

    @Cacheable(value = "balance", key = "'balance:' + #employeeId")
    public BigDecimal getLeaveBalance(Long employeeId) {

        log.info("Cache MISS → Fetching leave balance from DB for employee {}", employeeId);

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return employee.getLeaveBalance();
    }

}
