package tech.makers.BusinessLogic.user.administrator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.TransactionSystemException;
import tech.makers.BusinessLogic.user.User;
import tech.makers.BusinessLogic.user.UserRepository;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdminUserControllerIntegrationTest {
  @Autowired private MockMvc mvc;
  @Autowired private UserRepository repository;

  @Test
  public void testAdminsPostCreatesNewAdmin() throws Exception {
    mvc.perform(
            MockMvcRequestBuilders.post("/api/admin/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\", \"password\": \"pass\", \"isAdmin\": true}"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.user.username").value("user"));
    assertNotNull(repository.findByUsername("user"));
  }

  @Test
  public void testAdminsPostDoesNotCreateNewAdmin_IfUsernameAlreadyExists() throws Exception {
    repository.save(new User("user", "pass", true));
    assertEquals(1, repository.count());
    mvc.perform(
            MockMvcRequestBuilders.post("/api/admin/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\", \"password\": \"pass\", \"isAdmin\": true}"))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertEquals(true, result.getResolvedException() instanceof DataIntegrityViolationException));
    assertEquals(1, repository.count());
  }

  @Test
  public void testAdminsPostDoesNotCreateNewAdmin_IfUsernameBlank() throws Exception {
    mvc.perform(
            MockMvcRequestBuilders.post("/api/admin/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"\", \"password\": \"pass\", \"isAdmin\": true}"))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertEquals(true, result.getResolvedException() instanceof TransactionSystemException));
    assertEquals(0, repository.count());
  }
}
