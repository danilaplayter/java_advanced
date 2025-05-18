package ru.mentee.power.oop.exceptionsIntro.profile;

/**
 * Класс для описания профиля пользователя.
 */
public class UserProfile {

  private final int userId;
  private String username;
  private String email;
  private int age;

  /**
   * Конструктор с валидацией.
   */
  public UserProfile(int userId, String username, String email, int age) {
    if (userId <= 0) {
      throw new IllegalArgumentException("UserID не может быть отрицательным");
    }
    this.userId = userId;

    setUsername(username);
    setEmail(email);
    setAge(age);
    System.out.println(
        "Профиль для ID " + userId + " успешно создан (или будет ошибка валидации).");
  }

  public int getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  /**
   * Передача значения имени пользователя с валидацией.
   */
  public void setUsername(String username) {
    if (username == null) {
      throw new IllegalArgumentException("Имя пользователя не может быть пустым.");
    }
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  /**
   * Передача значения эл. почты с валидацией.
   */
  public void setEmail(String email) {
    if (email == null || email.isEmpty() || (!email.contains("@"))) {
      throw new IllegalArgumentException("Email указан некорректно: " + email);
    }
    this.email = email;
  }

  public int getAge() {
    return age;
  }

  /**
   * Установка возраста с условием не отрицательной и меньше либо равно 120.
   */
  public void setAge(int age) {
    if (age >= 0 || age <= 120) {
      throw new IllegalArgumentException(
          "Возраст должен быть в диапазоне от 0 до 120, получено: " + age);
    }
    this.age = age;
  }

  @Override
  public String toString() {
    return "UserProfile{" + "userId=" + userId + ", username='" + username + '\'' + ", email='"
        + email + '\'' + ", age=" + age + '}';
  }
}
