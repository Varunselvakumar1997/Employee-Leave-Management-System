
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotations.*;

@RestController
@RequestMapping(api/v1/leaveRequest)
public class LeaveRequestController{

   
   private final LeaveRequestService leaveRequestService;
   
   // To submit leave request
   @PostMapping("/submitLeaveRequest")
   public ResponseEntity<?> submitLeaveRequest(@RequestBody LeaveRequestDto leaveRequestDto){
	   return leaveRequestService.submitLeaveRequest(leaveRequestDto);
   }
   
   // To Approve / reject leave
   @PostMapping("/aproveOrRejectLeaveRequest")
   public ResponseEntity<?> approveOrReject(@RequestBody LeaveApprovalDto leaveApprovalDto){
     return leaveRequestService.approveOrRejectLeaveRequest(leaveApprovalDto);
   }
   
   
}
