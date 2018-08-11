package controller;

import com.kids.crm.controller.api.RestApiManager;
import com.kids.crm.model.Role;
import com.kids.crm.model.User;
import com.kids.crm.mongo.repository.UserLoginSessionRepository;
import com.kids.crm.repository.BatchRepository;
import com.kids.crm.repository.StudentBatchRepository;
import com.kids.crm.repository.StudentRepository;
import com.kids.crm.repository.SubjectRepository;
import com.kids.crm.service.JwtToken;
import com.kids.crm.service.StudentService;
import com.kids.crm.service.UserService;
import com.kids.crm.service.UserSession;
import org.junit.Before;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;

public class BaseController {

    @MockBean
    protected JwtToken jwtToken;
    @MockBean
    protected RestApiManager restApiManager;
    @MockBean
    protected StudentService studentService;
    @MockBean
    protected UserService userService;
    @MockBean
    protected UserLoginSessionRepository userLoginSessionRepository;
    @MockBean
    protected StudentRepository studentRepository;
    @MockBean
    protected BatchRepository batchRepository;
    @MockBean
    protected UserSession userSession;
    @MockBean
    protected StudentBatchRepository studentBatchRepository;
    @MockBean
    protected SubjectRepository subjectRepository;

    @Before
    public void setup(){
        User superUser = new User();
        superUser.setRole(Role.SUPER_ADMIN);
        superUser.setEmail("super@super.com");
        superUser.setId(1l);

        BDDMockito.given(userSession.getLoggedInUser()).willReturn(superUser);
    }

}
