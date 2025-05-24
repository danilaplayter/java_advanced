package ru.mentee.power.streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

class StreamOptionalTaskTest {

  private List<User> testUsers;
  private final String DEFAULT_EMAIL = "default@mail.com";

  @BeforeEach
  void setUp() {
    testUsers = List.of(
        new User("alice", "alice@example.com", true, 55),
        new User("bob", "bob@example.com", false, 20),
        new User("charlie", "charlie@sample.net", true, 105),
        new User("david", "david@spam.com", true, 0)
    );
  }

  @Test
  @DisplayName("findUserByUsername - пользователь найден")
  void findUserByUsername_Found() {
    Optional<User> userOpt = StreamOptionalTaskUtil.findUserByUsername(testUsers, "alice");
    assertThat(userOpt)
        .isPresent()
        .hasValueSatisfying(u -> assertThat(u.getUsername()).isEqualTo("alice"));
  }

  @Test
  @DisplayName("findUserByUsername - пользователь не найден")
  void findUserByUsername_NotFound() {
    Optional<User> userOpt = StreamOptionalTaskUtil.findUserByUsername(testUsers, "peter");
    assertThat(userOpt).isEmpty();
  }

  @Test
  @DisplayName("findUserByUsername - регистронезависимый поиск")
  void findUserByUsername_CaseInsensitive() {
    Optional<User> userOpt = StreamOptionalTaskUtil.findUserByUsername(testUsers, "ALICE");
    assertThat(userOpt)
        .isPresent()
        .hasValueSatisfying(u -> assertThat(u.getUsername()).isEqualTo("alice"));
  }

  @Test
  @DisplayName("getUserEmail - пользователь найден")
  void getUserEmail_Found() {
    String email = StreamOptionalTaskUtil.getUserEmail(testUsers, "alice", DEFAULT_EMAIL);
    assertThat(email).isEqualTo("alice@example.com");
  }

  @Test
  @DisplayName("getUserEmail - пользователь не найден")
  void getUserEmail_NotFound() {
    String email = StreamOptionalTaskUtil.getUserEmail(testUsers, "peter", DEFAULT_EMAIL);
    assertThat(email).isEqualTo(DEFAULT_EMAIL);
  }

  @Test
  @DisplayName("printActiveLoginCount - активный пользователь")
  void printActiveLoginCount_Active() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    StreamOptionalTaskUtil.printActiveLoginCount(testUsers, "alice");
    assertThat(outContent.toString()).contains("alice has 55 logins");

    System.setOut(System.out);
  }

  @Test
  @DisplayName("printActiveLoginCount - неактивный пользователь")
  void printActiveLoginCount_Inactive() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    StreamOptionalTaskUtil.printActiveLoginCount(testUsers, "bob");
    assertThat(outContent.toString()).contains("bob not found or inactive");

    System.setOut(System.out);
  }

  @Test
  @DisplayName("printActiveLoginCount - пользователь с 0 логинов")
  void printActiveLoginCount_ZeroLogins() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    StreamOptionalTaskUtil.printActiveLoginCount(testUsers, "david");
    assertThat(outContent.toString()).contains("david has 0 logins");

    System.setOut(System.out);
  }

  @Test
  @DisplayName("findUsernamesWithHighLoginCount - найдены пользователи")
  void findUsernamesWithHighLoginCount_Found() {
    List<String> usernames = StreamOptionalTaskUtil.findUsernamesWithHighLoginCount(testUsers, 50);
    assertThat(usernames).containsExactlyInAnyOrder("alice", "charlie");
  }

  @Test
  @DisplayName("findUsernamesWithHighLoginCount - не найдены пользователи")
  void findUsernamesWithHighLoginCount_NotFound() {
    List<String> usernames = StreamOptionalTaskUtil.findUsernamesWithHighLoginCount(testUsers, 200);
    assertThat(usernames).isEmpty();
  }

  @Test
  @DisplayName("Обработка null списка пользователей")
  void handleNullUserList() {
    assertThat(StreamOptionalTaskUtil.findUserByUsername(null, "alice")).isEmpty();
    assertThat(StreamOptionalTaskUtil.getUserEmail(null, "alice", DEFAULT_EMAIL)).isEqualTo(DEFAULT_EMAIL);
    assertThat(StreamOptionalTaskUtil.findUsernamesWithHighLoginCount(null, 50)).isEmpty();

    // Проверка printActiveLoginCount с null
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    StreamOptionalTaskUtil.printActiveLoginCount(null, "alice");
    assertThat(outContent.toString()).contains("alice not found or inactive");
    System.setOut(System.out);
  }
}