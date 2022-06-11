package com.chat.live.controller;

import com.chat.live.Service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class ChatController {

    private final SimpMessagingTemplate template;

    public ChatController(SimpMessagingTemplate template, ChatService chatRoomService) {
        this.template = template;
    }

    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage() {

    }

}
