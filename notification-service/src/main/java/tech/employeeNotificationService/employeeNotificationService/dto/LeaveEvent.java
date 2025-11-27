package tech.employeeNotificationService.employeeNotificationService.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveEvent {

    private String eventType;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int totalDays;
    private ZonedDateTime approvedAt;

}
