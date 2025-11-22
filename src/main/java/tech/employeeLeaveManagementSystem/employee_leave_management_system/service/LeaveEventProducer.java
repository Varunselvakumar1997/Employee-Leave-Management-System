package tech.employeeLeaveManagementSystem.employee_leave_management_system.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.dto.LeaveEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeaveEventProducer {

    private final KafkaTemplate<String, LeaveEvent> kafkaTemplate;

    private static final String TOPIC = "leave-events";

    public void publishLeaveApprovedEvent(LeaveEvent event) {
        log.info("Publishing LeaveEvent to Kafka: {}", event);
        kafkaTemplate.send(TOPIC, event);
    }

}
