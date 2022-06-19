package com.chat.live.controller;

import com.chat.live.dto.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate template; // Config 에서 @EnableWebSocketMessageBroker 를 통해서 등록되는 Bean. 특정 Broker 로 메시지를 전달.

    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/chat/join") // 실제 경로는 앞에 app, topic, queue 가 붙여진다. 현재는 publish, subscribe 도 붙을 수 있음
    public void join(ChatMessage message) { // ChatMessage 는 채팅 메시지를 담은 엔티티.
        log.info("join: {}", message);
        message.setMessage("[입장] " + message.getWriter());
        template.convertAndSend("/subscribe/chat/room" + message.getChatRoomId(), message); // 1:N 전송.(입장 메시지 이므로)
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        template.convertAndSend("/subscribe/char/room/" + message.getChatRoomId(), message); // 1:N 전송.(귓말이 아니므로)
    }





}
