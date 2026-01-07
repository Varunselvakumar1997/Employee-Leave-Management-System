package tech.employeeLeaveManagementSystem.employee_leave_management_system.controller;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.search.LeaveSearchDocument;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.search.LeaveSearchRepository;

import java.util.List;

@RestController
@RequestMapping("/leaves")
public class LeaveSearchController {

    private final ObjectProvider<LeaveSearchRepository> repositoryProvider;

    public LeaveSearchController(ObjectProvider<LeaveSearchRepository> repositoryProvider) {
        this.repositoryProvider = repositoryProvider;
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchLeaves(@RequestParam String query) {
        LeaveSearchRepository repository = repositoryProvider.getIfAvailable();
        if (repository == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Elasticsearch is not available. Please try again later.");
        }
        List<LeaveSearchDocument> results = repository.findByStatusContainingOrTypeContaining(query, query);
        return ResponseEntity.ok(results);
    }

}
