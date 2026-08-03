package com.ncv.security_testing;

import com.ncv.security_testing.dto.CustomResponse;
import com.ncv.security_testing.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SecurityTestingApplicationTests {
	@Autowired
	private PostRepository postRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCustomQuery(){
		List<CustomResponse> posts=postRepository.getALlCustomPosts();
		for(CustomResponse c:posts){
			System.out.println(c.getId()+" "+c.getTitle());
		}
	}

}
