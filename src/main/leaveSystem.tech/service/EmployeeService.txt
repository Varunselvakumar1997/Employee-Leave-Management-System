
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
  
  private final EmployeeRepo employeeRepo;
  
  private final LeaveRequestRepo leaveRequestRepo;
  
  
  // To create employee details
  public void saveEmployee(EmployeeSaveRequestDto employeeRequestSaveDto){
    
	Optional <Employee> employeeData = employeeRepo.findById(employeeId);
    Employee employee = employeeData.get();
  
    if(employee == null) {
       employee = new Employee();
    }
  
    employee.setName(employeeRequestSaveDto.getName);
    employee.setEmail(employeeRequestSaveDto.getEmail);
    employee.setJoiningDate(employeeRequestSaveDto.getJoiningDate);
    employee.setLeaveBalance(employeeRequestSaveDto.getLeaveBalance);
    employeeRepo.save(employee);
  }
  
  
  // Get leave balance of an employee
  public BigDecimal getLeaveBalance(Long employeeId){
   Optional <Employee> employeeData = employeeRepo.findById(employeeId);
   return employeeDate.get().getLeaveBalance();
  }
  
}