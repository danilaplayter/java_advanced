package ru.mentee.power.oop.encapsulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для класса UserProfile")
class UserProfileTest {

  private UserProfile userProfile;
  private final int userId = 1;
  private final String initialUsername = "testuser";
  private final String initialEmail = "test@example.com";

  @BeforeEach
  void setUp() {
    userProfile = new UserProfile(userId, initialUsername, initialEmail);
  }

  @Test
  @DisplayName("Конструктор должен корректно инициализировать поля")
  void constructorShouldInitializeFields() {
    assertThat(userProfile.getUserId()).isEqualTo(userId);
    assertThat(userProfile.getUsername()).isEqualTo(initialUsername);
    assertThat(userProfile.getEmail()).isEqualTo(initialEmail);
    assertThat(userProfile.getRegistrationDate()).isBeforeOrEqualTo(LocalDate.now());
  }

  @Test
  @DisplayName("Геттеры должны возвращать корректные значения")
  void gettersShouldReturnCorrectValues() {
    assertThat(userProfile.getUserId()).isEqualTo(userId);
    assertThat(userProfile.getUsername()).isEqualTo(initialUsername);
    assertThat(userProfile.getEmail()).isEqualTo(initialEmail);
    assertThat(userProfile.getRegistrationDate()).isNotNull();
  }

  @Test
  @DisplayName("setUsername должен обновлять имя пользователя для валидного ввода")
  void setUsernameShouldUpdateUsernameForValidInput() {
    String newUsername = "new_valid_username";
    userProfile.setUsername(newUsername);
    assertThat(userProfile.getUsername()).isEqualTo(newUsername);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"  ", "\t\n"})
  @DisplayName("setUsername не должен изменять имя при невалидном вводе")
  void setUsernameShouldNotUpdateUsernameForInvalidInput(String invalidUsername) {
    assertThatThrownBy(() -> userProfile.setUsername(invalidUsername))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Username cannot be null or empty");

    assertThat(userProfile.getUsername()).isEqualTo(initialUsername);
  }

  @Test
  @DisplayName("setEmail должен обновлять email для валидного ввода")
  void setEmailShouldUpdateEmailForValidInput() {
    String newEmail = "new.email@example.org";
    userProfile.setEmail(newEmail);
    assertThat(userProfile.getEmail()).isEqualTo(newEmail);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"plainaddress", "@missingusername.com", "test@domain_com"})
  @DisplayName("setEmail не должен изменять email при невалидном вводе")
  void setEmailShouldNotUpdateEmailForInvalidInput(String invalidEmail) {
    assertThat(userProfile.getEmail()).isEqualTo(initialEmail);
  }

  @Test
  @DisplayName("toString должен возвращать непустую строку")
  void toStringShouldReturnNonEmptyString() {
    String profileString = userProfile.toString();
    assertThat(profileString)
        .isNotBlank()
        .contains("userId=" + userId)
        .contains("username='" + initialUsername + "'")
        .contains("email='" + initialEmail + "'")
        .contains("registrationDate=");
  }
}