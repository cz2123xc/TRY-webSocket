package com.chat.live.controller;

import com.chat.live.dto.Greeting;
import com.chat.live.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.util.HtmlUtils;

@Controller
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate template; // Config 에서 @EnableWebSocketMessageBroker 를 통해서 등록되는 Bean. 특정 Broker 로 메시지를 전달.

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }


    @MessageMapping("/greeting")
    public void greeting(Greeting greeting) {
        log.info("greeting: {}", greeting.getChannelId());
        greeting.setMessage(greeting.getChannelId() + "번 채널에 아무개 님이 입장하셧습니다.");
        template.convertAndSend("/topic/chat/room/" + greeting.getChannelId() , greeting);
    }


    /**
     *
     * /pub/chat/message => /topic/chat/room/{channelId}
     */
    @MessageMapping("/request")
    public void handleMessageWithoutResponse(Message message) {
        log.info("request 컨트롤러 진입");
        template.convertAndSend("/topic/chat/room/" + message.getChannelId(), message); // 1:N 전송.(귓말이 아니므로)
    }






}
