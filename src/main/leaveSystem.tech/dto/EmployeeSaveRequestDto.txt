
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSaveRequestDto {
  
  private long id;
  private String name;
  private String email;
  private LocalDate joiningDate;
  private BigDecimal leaveBalance;
}