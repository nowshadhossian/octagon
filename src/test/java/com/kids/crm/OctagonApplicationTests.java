package com.kids.crm;

import com.kids.crm.exportdata.ExportQuestionData;
import com.kids.crm.model.Question;
import com.kids.crm.model.Role;
import com.kids.crm.model.Subject;
import com.kids.crm.model.User;
import com.kids.crm.repository.BoardRepository;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.repository.SubjectRepository;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.service.Encryption;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

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

	@Autowired
	ExportQuestionData exportQuestionData;

	@Autowired
	SubjectRepository subjectRepository;

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
	public void encryption(){
		System.out.println(Encryption.encrypt("happy"));
	}

	@Test
	@Ignore
	public void createQuestion(){
		/*Board board = Board.builder().name("GCE Cambridge").build();
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
				.build();*/
		final Subject subject = exportQuestionData.findOrCreateSubject("Physics");

		List<Question> questionList = exportQuestionData.readQuestionExcel();
        questionList.forEach(question1 -> {
        	question1.setSubject(subject);
			questionRepository.save(question1);
		});
	}

}
