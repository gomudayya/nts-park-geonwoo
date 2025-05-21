package com.nts.ntsboard.service;

import com.nts.ntsboard.controller.request.LoginRequest;
import com.nts.ntsboard.controller.response.UserResponse;
import com.nts.ntsboard.domain.User;
import com.nts.ntsboard.exception.InvalidLoginException;
import com.nts.ntsboard.repository.UserRepository;
import com.nts.ntsboard.common.auth.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(InvalidLoginException::new);

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new InvalidLoginException();
        }

        String token = jwtUtil.createToken(user.getId());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        return UserResponse.from(user);
    }
}
