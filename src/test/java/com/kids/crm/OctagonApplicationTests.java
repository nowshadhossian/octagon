package com.kids.crm;

import com.kids.crm.model.Role;
import com.kids.crm.model.User;
import com.kids.crm.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OctagonApplicationTests {
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	public void saveUser() {
		User user = new User();
		user.setEmail("test@test.com");
		user.setName("Test");
		user.setRole(Role.STUDENT);
		user.setPassword(passwordEncoder.encode("me2"));
		userRepository.save(user);
	}

}
