package com.nts.ntsboard.repository;

import com.nts.ntsboard.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
