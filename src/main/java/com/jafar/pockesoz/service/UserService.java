package com.jafar.pockesoz.service;

import com.jafar.pockesoz.entity.User;
import com.jafar.pockesoz.model.request.UserRequest;
import com.jafar.pockesoz.model.response.UserResponse;

public interface UserService {

    UserResponse create(UserRequest request);
    UserResponse getById(String id);
    User getUserById(String id);
    UserResponse getByEmail(String email);
    UserResponse update(UserRequest request);
    void deleteById(String id);

}
