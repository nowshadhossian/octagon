package com.kids.crm;

import com.kids.crm.model.*;
import com.kids.crm.repository.BoardRepository;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.repository.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OctagonApplicationTests {
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	BoardRepository boardRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Ignore
	@Test
	public void saveUser() {
		User user = new User();
		user.setEmail("test@test.com");
		user.setName("Test");
		user.setRole(Role.STUDENT);
		user.setPassword(passwordEncoder.encode("me2"));
		userRepository.save(user);
	}

	@Test
	public void createQuestion(){
		Board board = Board.builder().name("GCE Cambridge").build();
		boardRepository.save(board);

		Subject subject = Subject.builder()
				.board(board)
				.code("1324")
				.name("Physics")
				.build();

		Question question = Question.builder()
				.questionNo(52)
				.answer("D")
				.fileName("apple.jpg")
				.paper(21)
				.session(Session.builder()
						.name("Summer")
						.build())
				.subject(subject)
				.topic(Topic.builder()
							.name("General Physics")
							.subject(subject)
							.build())
				.subTopics(Set.of(SubTopic.builder()
						.name("Speed")
						.build(), SubTopic.builder()
						.name("Momentum")
						.build()))
				.build();

		questionRepository.save(question);

	}

}
