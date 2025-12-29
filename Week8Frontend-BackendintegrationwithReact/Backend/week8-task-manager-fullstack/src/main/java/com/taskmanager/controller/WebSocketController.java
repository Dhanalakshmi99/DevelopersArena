package com.taskmanager.controller;

import com.taskmanager.model.entity.Task;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendTaskUpdate(Task task) {
        messagingTemplate.convertAndSend("/topic/tasks", task);
    }
}
