package com.ncv.security_testing.controller;

import com.ncv.security_testing.dto.PostEntityDto;
import com.ncv.security_testing.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

//    @GetMapping("/posts")
//    @Secured({"ROLE_USER", "ROLE_CREATOR", "ROLE_ADMIN"})
//    public String getPosts(){
//        return "Hello From PostController";
//    }
//    @PostMapping("/posts")
//    @Secured({"ROLE_CREATOR", "ROLE_ADMIN"})
//    public String createPost(){
//        return "Post created";
//    }
    private final PostService postService;

    @GetMapping
    @Secured("ROLE_USER")
    public ResponseEntity<List<PostEntityDto>> getAllPost() {
        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('CREATOR') or hasRole('ADMIN')")
    public ResponseEntity<PostEntityDto> createPost(@RequestBody PostEntityDto postEntityDto) {
        return new ResponseEntity<>(postService.createPostEntity(postEntityDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasRole('USER') or hasRole('CREATOR') or hasRole('ADMIN')")
    public ResponseEntity<PostEntityDto> getPostById(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.getPostEntityById(postId), HttpStatus.OK);
    }
}
