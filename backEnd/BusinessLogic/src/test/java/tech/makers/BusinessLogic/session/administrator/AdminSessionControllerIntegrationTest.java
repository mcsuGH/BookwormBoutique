package tech.makers.BusinessLogic.session.administrator;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tech.makers.BusinessLogic.user.User;
import tech.makers.BusinessLogic.user.UserRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdminSessionControllerIntegrationTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private UserRepository repository;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

  @Test
  public void sessionPostLogsInUser_CorrectCredentials() throws Exception {
    repository.save(new User("user", passwordEncoder.encode("pass"), true));
    MvcResult result =
        mvc.perform(
            MockMvcRequestBuilders.post("/api/admin/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\", \"password\": \"pass\"}"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.user.username").value("user"))
            .andReturn();

    String response = result.getResponse().getContentAsString();
    String token = JsonPath.parse(response).read("$.token");

    mvc.perform(MockMvcRequestBuilders.get("/api/admin/session").header("Authorization", token))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.user.username").value("user"));
  }
}
