
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApprovalDto {
  
  private long leaveRequestId;
  private LeaveStatus leaveStatus;
  
}