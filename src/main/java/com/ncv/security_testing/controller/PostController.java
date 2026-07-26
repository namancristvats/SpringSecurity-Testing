package com.ncv.security_testing.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @GetMapping("/posts")
    @Secured({"ROLE_USER", "ROLE_CREATOR", "ROLE_ADMIN"})
    public String getPosts(){
        return "Hello From PostController";
    }
    @PostMapping("/posts")
    @Secured({"ROLE_CREATOR", "ROLE_ADMIN"})
    public String createPost(){
        return "Post created";
    }
}
