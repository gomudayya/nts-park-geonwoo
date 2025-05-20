package com.nts.ntsboard.repository.entity;

import com.nts.ntsboard.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.username = user.getUsername();
        userEntity.password = user.getPassword();
        userEntity.nickname = user.getNickname();
        return userEntity;
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
