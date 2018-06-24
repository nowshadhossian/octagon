package integration;

import com.kids.crm.OctagonApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OctagonApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpIntegrationTest {
    @Autowired private TestRestTemplate restTemplate;

    @Test
    public void signUpPageLoad(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/register", String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), 200);
        Assert.assertTrue(responseEntity.getBody().contains("name=\"firstName\""));
    }

    @Test
    public void signInPageLoad(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/login", String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), 200);
        Assert.assertTrue(responseEntity.getBody().contains("name=\"username\""));
    }

   /* @Test
    public void switchBadgePage(){

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("username", "now@now.com");
        map.add("password", "me");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> signInEntity = restTemplate.postForEntity("/login", request, String.class);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/student/switch-batch", String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), 200);
        Assert.assertTrue(responseEntity.getBody().contains("<title>Select Batch | Octagon</title>"));
    }
*/





}
