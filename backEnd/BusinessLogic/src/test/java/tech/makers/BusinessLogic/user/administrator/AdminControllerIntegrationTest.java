package tech.makers.BusinessLogic.user.administrator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tech.makers.BusinessLogic.user.UserRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdminControllerIntegrationTest {
  @Autowired private MockMvc mvc;
  @Autowired private UserRepository repository;

  @Test
  public void testAdminsPostCreatesNewAdmin() throws Exception {
    mvc.perform(
            MockMvcRequestBuilders.post("/api/admin/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\", \"password\": \"pass\", \"isAdmin\": true}"))
        .andExpect(status().isOk());
  }
}
