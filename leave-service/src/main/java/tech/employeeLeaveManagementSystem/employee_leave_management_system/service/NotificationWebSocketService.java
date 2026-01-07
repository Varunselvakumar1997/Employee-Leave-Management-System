package tech.employeeLeaveManagementSystem.employee_leave_management_system.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.dto.NotificationMessage;

import java.time.Instant;

@Service
@Slf4j
public class NotificationWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationWebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendLeaveStatusUpdate(
            String username,
            String messageText
    ) {
        NotificationMessage notification = new NotificationMessage(
                "LEAVE_STATUS_UPDATE",
                messageText,
                Instant.now()
        );

        // 🔹 Log the message (THIS IS WHAT YOU WANT)
        log.info("WebSocket Notification Sent: {}", notification);

        // 🔹 Send via WebSocket (no client needed)
        messagingTemplate.convertAndSendToUser(
                username,
                "/queue/notifications",
                notification
        );
    }

}
