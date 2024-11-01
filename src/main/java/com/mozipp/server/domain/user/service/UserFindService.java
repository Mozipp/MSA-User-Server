package com.mozipp.server.domain.user.service;

import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFindService {

    private final UserRepository userRepository;

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(null);
    }

    public User findByUserDetails(UserDetails userDetails) {
        return userRepository.findUserByUsername(userDetails.getUsername());
    }

}
