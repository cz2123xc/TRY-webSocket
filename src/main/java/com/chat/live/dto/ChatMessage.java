package com.chat.live.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 입력받는 생성자 자동 생성
public class ChatMessage {

    public enum MessageType {
        JOIN, LEAVE, MESSAGE
    }

    private String chatRoomId;
    private String writer;
    private String message;
    private MessageType messageType;

}
