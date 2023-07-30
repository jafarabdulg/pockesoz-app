package com.jafar.pockesoz.controller;

import com.jafar.pockesoz.model.request.UserRequest;
import com.jafar.pockesoz.model.response.CommonResponse;
import com.jafar.pockesoz.model.response.UserResponse;
import com.jafar.pockesoz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.create(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("success created new user")
                .data(userResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        UserResponse userResponse = userService.getById(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get user")
                .data(userResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getUserByEmail(@RequestParam(name = "email") String email) {
        UserResponse userResponse = userService.getByEmail(email);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get user")
                .data(userResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.update(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("success updated user")
                .data(userResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        userService.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("success deleted user")
                        .build()
                );
    }
}
