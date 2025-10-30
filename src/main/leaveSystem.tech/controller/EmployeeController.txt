
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.Requestbody;
import org.springframework.web.bind.annotation.restController;

@RestController
@RequestMapping(/api/employees)
public class Employeecontroller {
  
  private final EmployeeService employeeService;
  
  @PostMapping("/saveEmployeeDetails")
  public ResponseEntity<?> saveEmployee(@Requestbody EmployeeSaveRequestDto employeeSaveRequestDto){
   try {
      employeeService.saveEmployee(employeeSaveRequestDto);
	  return ResponseEntity.Ok("Employee saved successfully!")
   } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured while saving employee : " +e.getMessage())
   }
   
  }
  
  
}