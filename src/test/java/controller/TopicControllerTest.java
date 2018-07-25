package controller;

import com.kids.crm.OctagonApplication;
import com.kids.crm.config.Config;
import com.kids.crm.config.FreemarkerConfig;
import com.kids.crm.config.WebSecurityConfig;
import com.kids.crm.controller.superadmin.TopicController;
import com.kids.crm.model.Subject;
import com.kids.crm.model.Topic;
import com.kids.crm.repository.GuardianRepository;
import com.kids.crm.repository.StudentBatchInterestRepository;
import com.kids.crm.repository.StudentRefereeRepository;
import com.kids.crm.repository.SubjectRepository;
import com.kids.crm.service.BatchService;
import com.kids.crm.service.SubTopicService;
import com.kids.crm.service.SubjectService;
import com.kids.crm.service.TopicService;
import com.kids.crm.validator.SignupValidator;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TopicController.class)
@ContextConfiguration(classes = OctagonApplication.class)
@Import({WebSecurityConfig.class, Config.class, FreemarkerConfig.class})
public class TopicControllerTest extends BaseController{
    @Autowired
    private MockMvc mockMvc;


    @MockBean private SignupValidator validator;
    @MockBean private BatchService batchService;
    @Autowired private PasswordEncoder passwordEncoder;
    @MockBean private GuardianRepository guardianRepository;
    @MockBean private StudentBatchInterestRepository studentBatchInterestRepository;
    @MockBean private SubjectRepository subjectRepository;
    @MockBean private StudentRefereeRepository studentRefereeRepository;
    @MockBean private TopicService topicService;
    @MockBean private SubjectService subjectService;
    @MockBean private SubTopicService subTopicService;

    @Test
    @WithMockUser(username="super@super.com",roles={"SUPER_ADMIN"})
    public void topicPage_shouldLoad() throws Exception{
        mockMvc.perform(get("/superadmin/topic"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("abbreviateSubTopicMap", new HashMap<>()))
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("Topics")));
    }

    @Test
    @WithMockUser(username="super@super.com",roles={"SUPER_ADMIN"})
    public void topicShouldSave() throws Exception {
        Subject subject = Subject.builder().id(1l).build();
        mockMvc.perform(post("/superadmin/topic/save", Topic.builder().name("Haha").subject(subject).build()).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/superadmin/topic"));
    }

}
