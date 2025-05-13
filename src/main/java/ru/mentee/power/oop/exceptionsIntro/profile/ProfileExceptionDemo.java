package ru.mentee.power.oop.exceptionsIntro.profile;

/**
 * Класс для демонстрации работы класса UserProfile.
 */
public class ProfileExceptionDemo {

  /**
   * Основной метод.
   */
  public static void main(String[] args) {
    System.out.println("--- Демонстрация UserProfile с обработкой исключений ---");

    // Попытка создания профилей с различными данными
    System.out.println("1. Попытка создания корректного профиля:");
    try {
      UserProfile validProfile = new UserProfile(1, "John Doe", "john.doe@example.com", 30);
      System.out.println("Успешно создан: " + validProfile);

      System.out.println("Попытка установить корректный email:");
      validProfile.setEmail("new.john.doe@example.com");
      System.out.println("   Новый email: " + validProfile.getEmail());

      System.out.println("Попытка установить НЕкорректный возраст (-5):");
      validProfile.setAge(-5);

    } catch (IllegalArgumentException e) {
      System.err.println("   Перехвачена ошибка при работе с validProfile: " + e.getMessage());
    }

    System.out.println("\n2. Попытка создания профиля с невалидным ID (0):");
    try {
      UserProfile invalidIdProfile = new UserProfile(0, "Jane Doe", "jane@example.com", 25);
      System.out.println("Успешно создан: " + invalidIdProfile);
    } catch (IllegalArgumentException e) {
      System.err.println("   Перехвачена ошибка: " + e.getMessage());
    }

    System.out.println("\n3. Попытка создания профиля с пустым именем:");
    try {
      UserProfile emptyNameProfile = new UserProfile(2, " ", "empty@example.com", 20);
      System.out.println("Успешно создан: " + emptyNameProfile);
    } catch (IllegalArgumentException e) {
      System.err.println("   Перехвачена ошибка: " + e.getMessage());
    }

    System.out.println("\n4. Попытка создания профиля с некорректным email ('plainaddress'):");
    try {
      UserProfile invalidEmailProfile = new UserProfile(3, "Bob Smith", "plainaddress", 35);
      System.out.println("Успешно создан: " + invalidEmailProfile);
    } catch (IllegalArgumentException e) {
      System.err.println("   Перехвачена ошибка: " + e.getMessage());
    }

    System.out.println("\n5. Попытка создания профиля с возрастом 150:");
    try {
      UserProfile tooOldProfile = new UserProfile(4, "Old Man", "old@example.com", 150);
      System.out.println("Успешно создан: " + tooOldProfile);
    } catch (IllegalArgumentException e) {
      System.err.println("   Перехвачена ошибка: " + e.getMessage());
    }

    System.out.println("\n--- Демонстрация завершена ---");
  }
}