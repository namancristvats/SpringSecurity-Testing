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
    private final int PAGE_SIZE=5;

    @GetMapping
    @Secured("ROLE_USER")
    public ResponseEntity<List<PostEntityDto>> getAllPost(
            @RequestParam String sortBy,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        return new ResponseEntity<>(postService.getAllPost(pageNumber,PAGE_SIZE,sortBy), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('CREATOR') or hasRole('ADMIN')")
    public ResponseEntity<PostEntityDto> createPost(@RequestBody PostEntityDto postEntityDto) {
        return new ResponseEntity<>(postService.createPostEntity(postEntityDto), HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    @PreAuthorize("hasRole('CREATOR') or hasRole('ADMIN')")
    public ResponseEntity<PostEntityDto> getPostById(@PathVariable Long postId,@RequestBody PostEntityDto updatedDto) {
        PostEntityDto postEntityDto=postService.updatePost(postId,updatedDto);
        return new ResponseEntity<>(postEntityDto,HttpStatus.CREATED);

    }
}
