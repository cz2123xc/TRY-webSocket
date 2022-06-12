package com.chat.live.repository.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseToken {

    private String accessToken;
    private LocalDateTime expiryDate;

    public ResponseToken(String accessToken, LocalDateTime expiryDate) {
        this.accessToken = accessToken;
        this.expiryDate = expiryDate;
    }

}
