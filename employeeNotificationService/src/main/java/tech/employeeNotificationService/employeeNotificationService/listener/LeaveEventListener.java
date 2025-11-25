package tech.employeeNotificationService.employeeNotificationService.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tech.employeeNotificationService.employeeNotificationService.dto.LeaveEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class LeaveEventListener {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "leave-events", groupId = "notification-service-group")
    public void listen(String message) {
        try {
            LeaveEvent event = objectMapper.readValue(message, LeaveEvent.class);

            if ("LEAVE_APPROVED".equals(event.getEventType())) {
                log.info("Notification: Leave approved for employee {}", event.getEmployeeId());
                log.info("Simulated Email: Leave from {} to {} was approved.",
                        event.getStartDate(), event.getEndDate());
            }

        } catch (Exception e) {
            log.error("Failed to process event: {}", message, e);
        }
    }

}
