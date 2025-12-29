package com.taskmanager.service;

import com.taskmanager.model.entity.Task;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Broadcast a task status update to all connected clients
     */
    public void broadcastTaskStatusUpdate(Task task, String updatedBy) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("taskId", task.getId());
        payload.put("newStatus", task.getStatus());
        payload.put("updatedBy", updatedBy);
        payload.put("timestamp", LocalDateTime.now().toString());

        Map<String, Object> message = new HashMap<>();
        message.put("type", "TASK_UPDATED");
        message.put("payload", payload);

        // Cast to Object to avoid ambiguous method error
        messagingTemplate.convertAndSend("/topic/tasks", (Object) message);
    }

    /**
     * Broadcast a newly created task to all connected clients
     */
    public void broadcastNewTask(Task task, String createdBy) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", task.getId());
        payload.put("title", task.getTitle());
        payload.put("createdBy", createdBy);
        payload.put("timestamp", LocalDateTime.now().toString());

        Map<String, Object> message = new HashMap<>();
        message.put("type", "TASK_CREATED");
        message.put("payload", payload);

        // Cast to Object to avoid ambiguous method error
        messagingTemplate.convertAndSend("/topic/tasks", (Object) message);
    }
}
