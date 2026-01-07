package tech.employeeLeaveManagementSystem.employee_leave_management_system.search;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;

@Document(indexName = "leaves", createIndex = true)
public class LeaveSearchDocument {

    @Id
    private String id;

    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String type;
    private Integer totalDays;

    public LeaveSearchDocument(String id, Long employeeId, LocalDate startDate, LocalDate endDate, String status, String type, Integer totalDays) {
        this.id = id;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.type = type;
        this.totalDays = totalDays;
    }

    public LeaveSearchDocument() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }
}
