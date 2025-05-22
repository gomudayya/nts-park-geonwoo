package com.nts.ntsboard.repository;

import com.nts.ntsboard.domain.User;
import com.nts.ntsboard.exception.NotFoundException;
import com.nts.ntsboard.repository.entity.UserEntity;
import com.nts.ntsboard.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJpaRepository userJpaRepository;

    public User save(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toModel();
    }

    public User findById(Long userId) {
        return userJpaRepository.findById(userId).orElseThrow(() -> new NotFoundException("유저")).toModel();
    }

    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(UserEntity::toModel);
    }

    public boolean existByNickname(String nickname) {
        return userJpaRepository.existsByNickname(nickname);
    }
}
