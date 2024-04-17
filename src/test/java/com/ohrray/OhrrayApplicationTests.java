package com.ohrray;

import com.ohrray.entity.Member;
import com.ohrray.repository.LoginRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class OhrrayApplicationTests {
	@Autowired
	LoginRepository loginRepository;

	@Test
	void insertMember(){

		IntStream.rangeClosed(11,20).forEach(i->{
			Member member = Member.builder()
					.email("a"+i+"@a"+i+"com")
					.password("1234")
					.build();
			loginRepository.save(member);
		});


	}

	@Test
	void contextLoads() {

	}

}
