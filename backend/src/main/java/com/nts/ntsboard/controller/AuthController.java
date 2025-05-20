package com.nts.ntsboard.controller;

import com.nts.ntsboard.common.ApiResponse;
import com.nts.ntsboard.controller.request.LoginRequest;
import com.nts.ntsboard.controller.response.UserResponse;
import com.nts.ntsboard.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserResponse> login(@RequestBody LoginRequest loginRequest, HttpSession httpSession) {
        return ApiResponse.success(authService.login(loginRequest, httpSession), 200);
    }
}
