
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@AllargsConstructor
public class LeaveRequestService {
   
   private final LeaveRequestRepo leaveRequestRepo;
   
   private final EmployeeRepo employeeRepo;
   
   // Method for leave request
   @Transactional
   public ResponseEntity<?> submitLeaveRequest(LeaveRequestDto leaveRequestDto) {
      
	 Optional<Employee> employeeList = employeeRepo.findById(leaveRequestDto.getEmployeeId());
	 
	 if(employeeList.isEmpty){
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with this id " +leaveRequestDto.getEmployeeId+ " not found")
	 }
	 
	 Employee employee = employeeList.get();
	 
	 long daysRequested = ChronoUnit.DAYS.between(leaveRequestDto.getStartDate(),leaveRequestDto.getEndDate())
	 
	 if (leaveRequestDto.getType == LeaveType.PRIVILEGED && employee.getLeaveBalance().doubleValue() < daysRequested)) {
	   return ResponseEntity.badRequest().body("Your leave balance is insufficient, So you can't able to submit the request")
	 }
	 
	 LeaveRequest newLeaveRequest = new LeaveRequest();
	 newLeaveRequest.setEmployee(employee);
	 newleaveRequest.setStartDate(leaveRequestDto.getStartDate);
	 newLeaveRequest.setEndDate(leaveRequestDto.getEndDate);
	 newLeaveRequest.setType(leaveRequestDto.getType);
	 newLeaveRequest.setStatus(LeaveStatus.PENDING);
	 
	 leaveRequestRepo.save(newLeaveRequest);
	 
	 return ResponseEntity.status(HttpStatus.CREATED).body("Leave request submitted successfully");
   }
   
   
   // Approve or Reject leave request
   @Transactional
   public ResponseEntity<?> approveOrRejectLeaveRequest(LeaveApprovalDto leaveApprovalDto){
   
     Optional<LeaveRequest> leaveRequestList = leaveRequestRepo.findById(leaveApprovalDto.getLeaveRequestId());
	 
	 If(leaveRequestList.isEmpty){
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave request not found for the Id " +leaveApprovalDto.getLeaveRequestId());
	 }
	 
	 LeaveRequest existingRequest = leaveRequestList.get();
	 
	 If(existingRequest.getStatus() != LeaveStatus.PENDING) {
	   return ResponseEntity.badRequest().body("Only Pending leave requests can be approved or rejected!")
	 }
	 
	 existingRequest.setStatus(leaveApprovalDto.getStatus);
	 
	 if(leaveApprovalDto.getStatus == LeaveStatus.APPROVED && existingRequest.getType() == LeaveType.PRIVILEGED){
	    
		Employee employee = existingRequest.getEmployee();
		
		long daysRequested = ChronoUnit.DAYS.between(existingRequest.getStartDate(),existingRequest.getEndDate()) + 1;
		
		BigDecimal updatedBalance = employee.getLeaveBalance().subtract(BigDecimal.valueOf(daysRequested);
		
		employee.setLeaveBalance(updatedBalance);
		employeeRepo.save(employee);
	 }
	 
	 leaveRequestRepo.save(existingRequest);
	 
	 String message = (leaveApprovalDto.getStatus == LeaveStatus.APPROVED)
	 ? "Leave request approved successfully!" : "Leave request is rejected!"
	 
	 return ResponseEntity.status(HttpStatus.CREATED).body(message);
   }
   
  
}