
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDto {
  
  private Long employeeId;
  private LocalDate startDate;
  private LocalDate endDate;
  private LeaveType leaveType;
  
}
