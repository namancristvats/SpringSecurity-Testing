package com.ncv.security_testing.dto;

import com.ncv.security_testing.entity.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupDtoRequest {
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
}
