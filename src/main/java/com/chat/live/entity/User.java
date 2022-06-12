package com.chat.live.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Entity
@Getter @Setter
public class User {

    @Id
    private String id;

    @NotBlank
    private String userId;

    @NotNull
    private String userPw;

    @NotNull
    @Column(length = 30)
    private String nickname;


    public User() {
    }

    public User(String id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }



}
