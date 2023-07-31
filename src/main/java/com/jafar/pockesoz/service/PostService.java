package com.jafar.pockesoz.service;

import com.jafar.pockesoz.model.request.PostRequest;
import com.jafar.pockesoz.model.response.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse create(PostRequest request);
    PostResponse getById(String id);
    List<PostResponse> getAllByUserId(String userId);
    void update(PostRequest request);
    void deleteById(String id);
}
