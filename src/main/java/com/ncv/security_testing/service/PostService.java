package com.ncv.security_testing.service;

import com.ncv.security_testing.dto.PostEntityDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    public List<PostEntityDto> getAllPost();
    public PostEntityDto createPostEntity(PostEntityDto postEntityDto);
    public PostEntityDto getPostEntityById(Long id);
}
