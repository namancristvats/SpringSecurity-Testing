package com.ncv.security_testing.dto;


import lombok.Data;

@Data
public class LoginResponseDto {
    private long id;
    private String accessToken;
    private String refreshToken;

    public LoginResponseDto(long id, String accessToken, String refreshToken) {
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
