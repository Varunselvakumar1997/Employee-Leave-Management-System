import jakarta.persistence.*;
import lombok*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Data
@AllargsConstructor
@NoArgsConstructor
public class Employee{

@Id
@GeneratedValue(strategy = Generation.Type.Identity)
 private long id;
 private String name;
 private String email;
 private LocalDate joiningDate;
 private BigDecimal leaveBalance;
 
}
