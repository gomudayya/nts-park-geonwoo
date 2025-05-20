package com.nts.ntsboard.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private Long id;
    private String username;
    private String password;
    private String nickname;

    @Builder
    public User(Long id, String username, String password, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
