package com.ncv.security_testing.service;

import com.ncv.security_testing.dto.SignupDtoRequest;
import com.ncv.security_testing.dto.UserDto;
import com.ncv.security_testing.entity.User;
import com.ncv.security_testing.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final AuthRepository authRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto signup(SignupDtoRequest request) {
        User userExist=authRepository.findByEmail(request.getEmail());
        if(userExist!=null){
            throw new RuntimeException("User already exists");
        }
        User user=modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        authRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByEmail(username);
    }

    public User getUserById(Long userId) {
        return authRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

}
