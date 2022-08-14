package com.example.demo.service;

import com.example.demo.controller.dto.PostRequest;
import com.example.demo.controller.dto.PostResponse;
import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostResponse create(PostRequest postRequest) {
        final Post savedPost = postRepository.save(new Post(postRequest.getTitle(), postRequest.getContent()));
        return new PostResponse(savedPost.getId(), savedPost.getTitle(), savedPost.getContent());
    }

    public List<PostResponse> findAll() {
        final List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> PostResponse.from(post))
                .collect(Collectors.toUnmodifiableList());
    }

    public PostResponse findById(Long postId) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(NoSuchElementException::new);
        return PostResponse.from(post);
    }

    public void update(Long postId, PostRequest postRequest) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(NoSuchElementException::new);
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
