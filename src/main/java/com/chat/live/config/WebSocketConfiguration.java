package com.chat.live.config;

import com.chat.live.handler.StompHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
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
        registry.addEndpoint("/endpoint").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 2.  Application 내부에서 사용할 path 지정
        registry.setApplicationDestinationPrefixes("/publish"); // (/app) Broker 로 보내짐
        registry.enableSimpleBroker("/subscribe", "queue"); // (/topic) 메시지 바로 처리 queue = 1:1 메시지
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // 3. 유저의 CONNECT 와 DISCONNECT 할 때의 이벤트
        registration.interceptors(stompHandler);
    }

}

