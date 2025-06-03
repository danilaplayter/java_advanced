package ru.mentee.power.mvc.controller;
import ru.mentee.power.mvc.model.*;
import ru.mentee.power.mvc.view.StudentConsoleView;
import java.util.List;

public class StudentController {
  private final StudentModel model; // Зависимость от абстракции Модели
  private final StudentConsoleView view; // Зависимость от конкретного Представления (можно и абстракцию, если View несколько)

  public StudentController(StudentModel model, StudentConsoleView view) {
    if(model == null || view == null) {
      throw new NullPointerException("Model или view не может быть null");
    }
    this.model = model;
    this.view = view;
  }

  public void run() {
    boolean running = true;
    while (running) {
      view.displayMenu(); // 1. Показать меню
      String command = view.getCommand(); // 2. Получить команду от пользователя

      // 3. Обработать команду
      switch (command) {
        case "1": // Добавить студента
          String name = view.promptForStudentName(); // Запросить имя
          if (name != null && !name.isBlank()) {
            // Создаем "запрос" на создание студента (ID будет присвоен в модели)
            Student newStudent = Student.builder().name(name).build();
            model.addStudent(newStudent); // Передаем Модели для обработки
            view.displayMessage(
                "Студент \"" + name + "\" успешно добавлен с ID: " + findStudentIdByName(
                    name)); // Показываем результат
          } else {
            view.displayError("Имя студента не может быть пустым. Попробуйте еще раз.");
          }
          break;
        case "2": // Показать всех студентов
          List<Student> students = model.getAllStudents(); // Запросить данные у Модели
          view.displayStudentList(students); // Передать Представлению для отображения
          break;
        case "0": // Выход
          running = false;
          view.displayMessage("Спасибо за использование! До новых встреч!");
          break;
        default:
          view.displayError(
              "Неизвестная команда: " + command + ". Пожалуйста, выберите пункт из меню.");
          break;
      }
    }
  }

  private long findStudentIdByName(String name) {
    return model.getAllStudents().stream()
        .filter(s -> s.getName().equals(name))
        .mapToLong(Student::getId)
        .findFirst()
        .orElse(-1L);
  }
}
