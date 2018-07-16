package com.kids.crm;

import com.kids.crm.config.Config;
import com.kids.crm.exportdata.ExportQuestionData;
import com.kids.crm.model.*;
import com.kids.crm.model.mongo.UserLoginSession;
import com.kids.crm.mongo.repository.UserLoginSessionRepository;
import com.kids.crm.pojo.ExamSettingsDTO;
import com.kids.crm.repository.*;
import com.kids.crm.service.Encryption;
import com.kids.crm.service.MailSender;
import com.kids.crm.service.QuestionService;
import com.kids.crm.utils.DateUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

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

	@Autowired
	UserLoginSessionRepository userLoginSessionRepository;

	@Autowired
	MailSender emailSender;

	@Autowired
	QuestionService questionService;

	@Autowired private StudentAnswerRepository studentAnswerRepository;

	@Autowired
	Config config;

	@Ignore
	@Test
	public void saveUser() {
		User user = new User();
		user.setEmail("test@test.com");
		user.setFirstName("Test");
		user.setRole(Role.STUDENT);
		user.setPassword(passwordEncoder.encode("me2"));
		userRepository.save(user);
	}

	@Test
	@Ignore
	public void saveSecondUser() {
		User user = new User();
		user.setEmail("second@test.com");
		user.setFirstName("Second Hossain");
		user.setRole(Role.STUDENT);
		user.setPassword(passwordEncoder.encode("me2"));
		userRepository.save(user);
	}

	@Test
	@Ignore
	public void saveTeacher() {
		User user = new User();
		user.setEmail("teacher@test.com");
		user.setFirstName("Talukdar Hossain");
		user.setRole(Role.TEACHER);
		user.setPassword(passwordEncoder.encode("me2"));
		userRepository.save(user);
	}

	@Test
	@Ignore
	public void saveUserLoginSession(){
		UserLoginSession userLoginSession = UserLoginSession.builder()
				.email("b@b.com")
				.name("Happy")
				.build();
		userLoginSessionRepository.save(userLoginSession);
	}

	@Test
	public void readConfig(){
		System.out.println(config.getExamUiDomain() + " " + config.getCompanyName());
		Assert.assertTrue(true);
	}

	@Test
	public void encryption(){
		System.out.println(Encryption.encrypt("happy"));
	}

	@Test
	public void sendEmailToParents(){
		emailSender.sendEmailToParentsWithDailyExamResult();
	}

	@Test
	public void lombokEqualChecker(){
		Subject subject = new Subject();
		subject.setCode("" + 123);
		Batch b = new Batch();
		b.setId(2);
		b.setSubject(subject);

		Batch b2 = new Batch();
		b2.setId(2);

		Assert.assertTrue(b2.equals(b));


	}

	@Test
	public void yesterdayDailyExamResult(){
		Date yesterdayStart = DateUtils.toDate(LocalDate.now().minusDays(1));
		Date yesterdayEnd = DateUtils.toDate(LocalDate.now());

		User user = userRepository.findByEmail("test@test.com")
				.orElse(null);
		List<StudentAnswer> list = studentAnswerRepository.findByUserAndAttendedOnBetweenAndExamType(user, yesterdayStart, yesterdayEnd, ExamType.DAILY_EXAM);
		Assert.assertEquals(list.size(), 2);
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
		final Subject subject = exportQuestionData.findOrCreateSubject("Medical");

		List<Question> questionList = exportQuestionData.readQuestionExcel(subject);
        questionList.forEach(question1 -> {
        	question1.setSubject(subject);
			questionRepository.save(question1);
		});
	}

	@Test
	public void queryDslReturningCorrectTopic(){
		Iterable<Question> questions = questionService.findQuestionsByExamSettings(1, ExamSettingsDTO.builder().topicId(2).year(2017).build());
		Question question = questions.iterator().next();
		Assert.assertTrue(question.getYear() == 2017);
		Assert.assertTrue(question.getTopic().getId() == 2);
	}

}
