package com.ncv.security_testing.service;

import com.ncv.security_testing.dto.PostEntityDto;
import com.ncv.security_testing.entity.PostEntity;
import com.ncv.security_testing.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostEntityDto> getAllPost() {
        List<PostEntity> postEntities=postRepository.findAll();
        List<PostEntityDto> entitiesDtos=postEntities.stream()
                .map(entity->modelMapper.map(entity,PostEntityDto.class)).collect(Collectors.toList());
        return entitiesDtos;
    }

    @Override
    public PostEntityDto createPostEntity(PostEntityDto postEntityDto) {
        PostEntity postEntity=modelMapper.map(postEntityDto,PostEntity.class);
        postRepository.save(postEntity);
        PostEntityDto returnObject=modelMapper.map(postEntity,PostEntityDto.class);
        return returnObject;
    }

    @Override
    public PostEntityDto getPostEntityById(Long id) {
        return modelMapper.map(postRepository.findById(id).orElse(null), PostEntityDto.class);
    }
}
