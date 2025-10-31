import jakarta.persistence.*
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest{
  
  @Id
  @GenaratedValue(startegy = Genaration.Type.Identity)
  private long id;
  
  @ManyToOne(fetch = fetchType.LAZY)
  @JoinColumn(name = "employee_id",nullable = false)
  private Employee employee;
  
  private LocalDate startDate
  
  private LocalDate endDate;
  
  @Enumerated(EnumType.STRING)
  private LeaveStatus status;
  
  @Enumerated(EnumType.STRING)
  private LeaveType type;

}