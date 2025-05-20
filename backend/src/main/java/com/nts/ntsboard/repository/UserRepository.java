package com.nts.ntsboard.repository;

import com.nts.ntsboard.domain.User;
import com.nts.ntsboard.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJpaRepository userJpaRepository;
    public User save(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toModel();
    }
}
