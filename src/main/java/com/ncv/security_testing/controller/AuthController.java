package com.ncv.security_testing.controller;

import com.ncv.security_testing.dto.LoginRequestDto;
import com.ncv.security_testing.dto.LoginResponseDto;
import com.ncv.security_testing.dto.SignupDtoRequest;
import com.ncv.security_testing.dto.UserDto;
import com.ncv.security_testing.service.AuthService;
import com.ncv.security_testing.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/auth")
    public String getMethod() {
        return "Hello From AuthController";
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDtoRequest request) {
        UserDto userDto = userService.signup(request);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request, HttpServletRequest HttpRequest,
                                                  HttpServletResponse response) {
        LoginResponseDto loginResponse = authService.login(request);

        Cookie cookie = new Cookie("refreshToken", loginResponse.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {
        String refreshToken= Arrays.stream(request.getCookies())
                .filter(cookie->"refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie->cookie.getValue())
                .orElseThrow(()->new AuthenticationServiceException("Refresh Token not found in inside the request header"));
        LoginResponseDto loginResponseDto=authService.refresh(refreshToken);
        return ResponseEntity.ok(loginResponseDto);
    }
}

