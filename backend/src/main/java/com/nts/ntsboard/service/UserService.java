package com.nts.ntsboard.service;

import com.nts.ntsboard.controller.request.SignUpRequest;
import com.nts.ntsboard.controller.response.UserInfoResponse;
import com.nts.ntsboard.domain.User;
import com.nts.ntsboard.repository.UserRepository;
import jakarta.transaction.TransactionScoped;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserInfoResponse signUp(SignUpRequest signUpRequest) {
        String encodedPassword = passwordEncoder.encode(signUpRequest.password());
        User user = User.builder()
                .username(signUpRequest.username())
                .password(encodedPassword)
                .nickname(signUpRequest.nickname())
                .build();

        user = userRepository.save(user);
        return UserInfoResponse.from(user);
    }

    @Transactional(readOnly = true)
    public UserInfoResponse getUser(Long userId) {
        User user = userRepository.findById(userId);
        return UserInfoResponse.from(user);
    }
}
