package com.jafar.pockesoz.service.impl;

import com.jafar.pockesoz.entity.Pocket;
import com.jafar.pockesoz.entity.User;
import com.jafar.pockesoz.model.request.PocketRequest;
import com.jafar.pockesoz.model.request.UserRequest;
import com.jafar.pockesoz.model.response.UserResponse;
import com.jafar.pockesoz.repository.UserRepository;
import com.jafar.pockesoz.service.PocketService;
import com.jafar.pockesoz.service.UserService;
import com.jafar.pockesoz.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserResponse create(UserRequest request) {
        validationUtil.validate(request);

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .isActive(true)
                .build();
        userRepository.saveAndFlush(user);

        return UserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserResponse getById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user  not found"));

        return UserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user  not found"));
    }

    @Override
    public UserResponse getByEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        return UserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserResponse update(UserRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user  not found"));

        user.setName(request.getName());

        return UserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user  not found"));
        user.setIsActive(false);
    }
}
