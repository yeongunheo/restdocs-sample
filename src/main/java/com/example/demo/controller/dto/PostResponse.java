package com.example.demo.controller.dto;

import com.example.demo.domain.Post;

public class PostResponse {

    private Long id;
    private String title;
    private String content;

    public PostResponse() {
    }

    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
