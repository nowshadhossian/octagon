package controller;

import com.kids.crm.OctagonApplication;
import com.kids.crm.config.Config;
import com.kids.crm.config.FreemarkerConfig;
import com.kids.crm.config.WebSecurityConfig;
import com.kids.crm.controller.superadmin.SubTopicController;
import com.kids.crm.model.SubTopic;
import com.kids.crm.model.Topic;
import com.kids.crm.service.SubTopicService;
import com.kids.crm.service.TopicService;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SubTopicController.class)
@ContextConfiguration(classes = OctagonApplication.class)
@Import({WebSecurityConfig.class, Config.class, FreemarkerConfig.class})
public class SubTopicControllerTest extends BaseController{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topicService;
    @MockBean
    SubTopicService subTopicService;

    @Test
    @WithMockUser(username="super@super.com",roles={"SUPER_ADMIN"})
    public void subTopicEdit_badUrl() throws Exception {
        mockMvc.perform(get("/superadmin/sub-topic/edit").param("subTopicId", ""+1))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @WithMockUser(username="super@super.com",roles={"SUPER_ADMIN"})
    public void subTopicEdit_shouldOpen() throws Exception {
        long subTopicId = 1;

        BDDMockito.given(subTopicService.getSubTopicById(subTopicId)).willReturn(Optional.of(SubTopic.builder()
                .id(subTopicId)
                .name("Astro")
                .topic(Topic.builder().id(1l).name("Physics").build())
                .build()));

        mockMvc.perform(get("/superadmin/sub-topic/{subTopicId}/edit", subTopicId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("Sub-Topic")));
    }
}
