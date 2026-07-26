package com.ncv.security_testing.service;

import com.ncv.security_testing.dto.LoginRequestDto;
import com.ncv.security_testing.dto.LoginResponseDto;
import com.ncv.security_testing.dto.SignupDtoRequest;
import com.ncv.security_testing.dto.UserDto;
import com.ncv.security_testing.entity.User;
import com.ncv.security_testing.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService  {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final SessionService sessionService;


    public LoginResponseDto login(LoginRequestDto request) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        User user= (User) authentication.getPrincipal();

        String accessToken= jwtService.generateAccessToken(user);
        String refreshToken= jwtService.generateRefreshToken(user);
        sessionService.generateNewSession(user,refreshToken);
        return new LoginResponseDto(user.getId(),accessToken,refreshToken);
    }

    public LoginResponseDto refresh(String refreshToken) {
        Long userId=jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);
        User user=userService.getUserById(userId);
        String accessToken= jwtService.generateAccessToken(user);
        return new LoginResponseDto(userId,accessToken,refreshToken);
    }
}
