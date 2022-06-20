package com.chat.live.config;

import com.chat.live.handler.StompHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {


    private final StompHandler stompHandler; // 핸들러

    @Autowired
    public WebSocketConfiguration(StompHandler stompHandler) {
        // 생성자 핸들러 주입
        this.stompHandler = stompHandler;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 1. 핸드쉐이크 통신 담당할 엔드포인트 지정
        log.info("엔드포인트 진입");
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); // withSockJS() 는 삭제했다. 이유는 프론트에서 SockJS로 통신하지 않으면 오류가 난다.
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        log.info("메시지 브로커 설정");
        log.info("config: {}", config);
        // 2.  Application 내부에서 사용할 path 지정
        config.setApplicationDestinationPrefixes("/app"); // (pub) Client 에서 SEND 되는 메시지를 처리 (@MessageMapping @Controller 로 라우팅 되는 메시지)
        config.enableSimpleBroker("/topic", "/queue"); // (sub) 해당하는 경로를 SUBSCRIBE 하는 Client 에게 메시지를 간단하게 전달 queue = 1:1 메시지 (내장된 브로커 사용)
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // 3. 유저의 CONNECT 와 DISCONNECT 할 때의 이벤트
        registration.interceptors(stompHandler);
    }

}

