package tech.employeeLeaveManagementSystem.employee_leave_management_system.search;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Lazy
public interface LeaveSearchRepository extends ElasticsearchRepository<LeaveSearchDocument, String> {

    List<LeaveSearchDocument> findByStatusContainingOrTypeContaining(String status, String type);
}
