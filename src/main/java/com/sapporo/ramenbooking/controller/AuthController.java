package com.sapporo.ramenbooking.controller;

import com.sapporo.ramenbooking.dto.request.LoginRequest;
import com.sapporo.ramenbooking.dto.response.LoginResponse;
import com.sapporo.ramenbooking.dto.request.CurrentUserRequest;
import com.sapporo.ramenbooking.dto.response.CurrentUserResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    // private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword())) {
            return ResponseEntity.ok(LoginResponse.builder()
                    .token("demo-token")
                    .username(request.getUsername())
                    .role("ADMIN")
                    .message("Đăng nhập thành công")
                    .build());
        }
        return ResponseEntity.status(401).body(LoginResponse.builder()
                .message("Sai tên đăng nhập hoặc mật khẩu")
                .build());
    }

    @PostMapping("/me")
    public ResponseEntity<CurrentUserResponse> getCurrentUser(@RequestBody CurrentUserRequest request) {
        return ResponseEntity.ok(CurrentUserResponse.builder()
                .message("Thông tin người dùng được xác thực")
                .build());
    }

}