package com.jafar.pockesoz.controller;

import com.jafar.pockesoz.model.request.PostRequest;
import com.jafar.pockesoz.model.response.CommonResponse;
import com.jafar.pockesoz.model.response.PostResponse;
import com.jafar.pockesoz.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/posts")
public class PostController {

    final private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest request) {
        PostResponse postResponse = postService.create(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("success created post")
                .data(postResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPostById(@PathVariable String id) {
        PostResponse postResponse = postService.getById(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get post")
                .data(postResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping(path = "/myPosts/{userId}")
    public ResponseEntity<?> getAllPostByUserId(@PathVariable String userId) {
        List<PostResponse> postResponses = postService.getAllByUserId(userId);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get post")
                .data(postResponses)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<?> updatePost(@RequestBody PostRequest request) {
        postService.update(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("success updated post")
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable String id) {
        postService.deleteById(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("success deleted post")
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
