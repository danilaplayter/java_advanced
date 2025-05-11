package ru.mentee.power.oop.encapsulation;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс для описания пользователя.
 */
public class UserProfile {

  private final int userId;
  private String username;
  private String email;
  private final LocalDate registrationDate;

  /**
   * Конструктор класса по всем полям.
   */
  public UserProfile(int userId, String username, String email) {
    this.userId = userId;
    this.registrationDate = LocalDate.now();
    setUsername(username);
    setEmail(email);
  }

  // --- Геттеры ---
  public int getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  /**
   * Сеттеры с валидацией.
   */
  public void setUsername(String username) {
    if (username == null || username.trim().isEmpty()) {
      throw new IllegalArgumentException("Username cannot be null or empty");
    }
    this.username = username;
  }

  /**
   * Сеттер с валидацией.
   */
  public void setEmail(String email) {
    if (email == null || !email.contains("@")) {
      throw new IllegalArgumentException("Email must be valid and contain '@'");
    }
    this.email = email;
  }

  @Override
  public String toString() {
    return "UserProfile{" + "userId=" + userId + ", username='" + username + '\'' + ", email='"
        + email + '\'' + ", registrationDate=" + registrationDate + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserProfile that = (UserProfile) o;
    return userId == that.userId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }
}