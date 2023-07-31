package com.jafar.pockesoz.service.impl;

import com.jafar.pockesoz.entity.Post;
import com.jafar.pockesoz.entity.User;
import com.jafar.pockesoz.model.request.PostRequest;
import com.jafar.pockesoz.model.response.PostResponse;
import com.jafar.pockesoz.repository.PostRepository;
import com.jafar.pockesoz.service.PostService;
import com.jafar.pockesoz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public PostResponse create(PostRequest request) {
        User user = userService.getUserById(request.getUserId());

        Post post = Post.builder()
                .user(user)
                .content(request.getContent())
                .time(LocalDateTime.now())
                .build();
        postRepository.saveAndFlush(post);

        return postToResponse(post);
    }

    @Override
    public PostResponse getById(String id) {
        Post post = findUserById(id);
        return postToResponse(post);
    }

    @Override
    public List<PostResponse> getAllByUserId(String userId) {
        List<Post> posts = postRepository.findAllByUser_Id(userId);

        return posts.stream()
                .map(this::postToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void update(PostRequest request) {
        Post post = findUserById(request.getId());
        post.setContent(request.getContent());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteById(String id) {
        Post post = findUserById(id);
        postRepository.deleteById(id);
    }

    private PostResponse postToResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .time(String.valueOf(post.getTime()))
                .userId(post.getUser().getId())
                .build();
    }

    private Post findUserById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "post not found"));
    }
}
