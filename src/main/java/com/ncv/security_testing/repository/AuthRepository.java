package com.ncv.security_testing.repository;

import com.ncv.security_testing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
