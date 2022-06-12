package com.chat.live.repository.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Getter @Setter
public class LoginForm {

    @NotBlank
    private String userId;

    @NotBlank
    private String userPw;

}
