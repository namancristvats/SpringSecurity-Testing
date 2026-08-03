package com.ncv.security_testing.repository;

import com.ncv.security_testing.dto.CustomResponse;
import com.ncv.security_testing.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
    @Query("select p.id as id,p.title as title from PostEntity p")
    List<CustomResponse> getALlCustomPosts();
}
