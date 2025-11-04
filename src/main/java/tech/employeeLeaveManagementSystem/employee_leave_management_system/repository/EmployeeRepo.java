package tech.employeeLeaveManagementSystem.employee_leave_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

}
