package ru.mentee.power.oop.encapsulation;

/**
 * Класс для демонстрации работы UserProfile.
 */
public class ProfileDemo {

  /**
   * Основной метод.
   */
  public static void main(String[] args) {
    System.out.println("--- Демонстрация Инкапсуляции UserProfile ---");

    // 1. Создаем UserProfile с валидными данными
    UserProfile user1 = new UserProfile(1, "ivan_mentee", "ivan@example.com");

    // 2. Выводим информацию через toString()
    System.out.println("\nИнформация о профиле (toString()):");
    System.out.println(user1);

    // 3. Выводим отдельные поля через геттеры
    System.out.println("\nОтдельные поля профиля:");
    System.out.println("ID: " + user1.getUserId());
    System.out.println("Логин: " + user1.getUsername());
    System.out.println("Email: " + user1.getEmail());
    System.out.println("Дата регистрации: " + user1.getRegistrationDate());

    // 4. Пробуем изменить на валидные значения
    System.out.println("\nУстанавливаем валидные значения:");
    user1.setUsername("ivan_mentee_updated");
    user1.setEmail("ivan.updated@example.com");
    System.out.println("Обновленный профиль: " + user1);

    // 5. Пробуем изменить на невалидные значения
    System.out.println("\nПробуем невалидные значения:");
    try {
      user1.setUsername(null);
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка при установке username: " + e.getMessage());
    }

    try {
      user1.setEmail("invalid-email-no-at");
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка при установке email: " + e.getMessage());
    }

    // 6. Создаем профиль с невалидным email
    System.out.println("\nПопытка создания с невалидным email:");
    try {
      UserProfile user2 = new UserProfile(2, "test", "not-an-email");
    } catch (IllegalArgumentException e) {
      System.out.println("Не удалось создать профиль: " + e.getMessage());
    }

    // 7. Попытка прямого доступа к приватному полю (закомментировано, так как не скомпилируется)
    System.out.println("\nПопытка прямого доступа к полям:");
    // user1.username = "hack_attempt"; // Ошибка компиляции - поле приватное
    System.out.println("Прямое изменение приватных полей невозможно - код не компилируется");

    System.out.println("\n--- Демонстрация Завершена ---");
  }
}