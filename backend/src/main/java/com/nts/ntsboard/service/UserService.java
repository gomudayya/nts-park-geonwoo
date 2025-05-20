package com.nts.ntsboard.service;

import com.nts.ntsboard.controller.request.SignUpRequest;
import com.nts.ntsboard.controller.response.UserResponse;
import com.nts.ntsboard.domain.User;
import com.nts.ntsboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse signUp(SignUpRequest signUpRequest) {
        String encodedPassword = passwordEncoder.encode(signUpRequest.password());
        User user = User.builder()
                .username(signUpRequest.username())
                .password(encodedPassword)
                .nickname(signUpRequest.nickname())
                .build();

        user = userRepository.save(user);
        return UserResponse.from(user);
    }
}
