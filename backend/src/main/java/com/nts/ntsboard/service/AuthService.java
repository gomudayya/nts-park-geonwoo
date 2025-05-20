package com.nts.ntsboard.service;

import com.nts.ntsboard.controller.request.LoginRequest;
import com.nts.ntsboard.controller.response.UserResponse;
import com.nts.ntsboard.domain.User;
import com.nts.ntsboard.exception.InvalidLoginException;
import com.nts.ntsboard.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse login(LoginRequest loginRequest, HttpSession httpSession) {
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(InvalidLoginException::new);

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new InvalidLoginException();
        }

        httpSession.setAttribute("userId", user.getId());
        return UserResponse.from(user);
    }
}
