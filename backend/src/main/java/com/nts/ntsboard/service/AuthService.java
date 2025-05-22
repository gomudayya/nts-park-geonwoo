package com.nts.ntsboard.service;

import com.nts.ntsboard.controller.request.LoginRequest;
import com.nts.ntsboard.controller.response.UserInfoResponse;
import com.nts.ntsboard.domain.User;
import com.nts.ntsboard.exception.InvalidAuthentication;
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

    public UserInfoResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(InvalidAuthentication::new);

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new InvalidAuthentication();
        }

        String token = jwtUtil.createToken(user.getId());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        return UserInfoResponse.from(user);
    }
}
