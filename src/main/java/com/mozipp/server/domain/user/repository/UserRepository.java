package com.mozipp.server.domain.user.repository;

import com.mozipp.server.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    User findUserByUsername(String username);
}
