package ru.mentee.power.streams;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Book {
  private String title;
  private String author;
  private int year;
  private List<String> genres;

  public Book(String title, String author, int year, List<String> genres) {
    this.title = title;
    this.author = author;
    this.year = year;
    this.genres = genres;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setGenres(List<String> genres) {
    this.genres = genres;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getYear() {
    return year;
  }

  public List<String> getGenres() {
    return genres;
  }
}

public class StreamTransformTask {

  public static void main(String[] args) {
    List<Book> library = List.of(
        new Book("Игра Эндера", "Орсон Скотт Кард", 1985, List.of("Научная фантастика", "Роман")),
        new Book("Дюна", "Фрэнк Герберт", 1965, List.of("Научная фантастика", "Приключения")),
        new Book("1984", "Джордж Оруэлл", 1949, List.of("Антиутопия", "Социальная фантастика")),
        new Book("Гарри Поттер и философский камень", "Дж. К. Роулинг", 1997, List.of("Фэнтези", "Приключения")),
        new Book("Автостопом по галактике", "Дуглас Адамс", 1979, List.of("Научная фантастика", "Комедия")),
        new Book("Задача трех тел", "Лю Цысинь", 2008, List.of("Научная фантастика", "Триллер", "фантастика")),
        new Book("Гиперион", "Дэн Симмонс", 1989, List.of("Научная фантастика", "Космическая опера")),
        new Book("Цветы для Элджернона", "Дэниел Киз", 1966, List.of("Научная фантастика", "Психологическая драма")),
        new Book("Марсианин", "Энди Вейер", 2011, List.of("Научная фантастика", "Выживание")),
        new Book("Ведьмак. Последнее желание", "Анджей Сапковский", 1993, List.of("Фэнтези", "Сборник рассказов")),
        new Book("Американские боги", "Нил Гейман", 2001, List.of("Фэнтези", "Мифология", "Роман")),
        new Book("Левая рука тьмы", "Урсула Ле Гуин", 1969, List.of("Научная фантастика", "Социальная фантастика")),
        new Book("Властелин колец", "Дж. Р. Р. Толкин", 1954, List.of("Фэнтези", "Эпическое фэнтези"))
    );

    System.out.println("--- Исходная библиотека ---");
    library.forEach(System.out::println);

    // --- Задача 1: Названия книг (Фантастика, >2000г) ---
    System.out.println("\n--- Названия книг (Фантастика, >2000г) ---");
    List<String> scienceFictionBooksAfter2000 = StreamTransformTaskUtil.findSciFiTitlesAfter2000(library);
    System.out.println(scienceFictionBooksAfter2000);

    // --- Задача 2: Уникальные авторы (отсортированные) ---
    System.out.println("\n--- Уникальные авторы (отсортированные) ---");
    List<String> uniqueAuthorsSorted = StreamTransformTaskUtil.findUniqueAuthors(library);
    System.out.println(uniqueAuthorsSorted);

    // --- Задача 3: Уникальные жанры (в верхнем регистре) ---
    System.out.println("\n--- Уникальные жанры (в верхнем регистре) ---");
    Set<String> uniqueGenresUpperCase = StreamTransformTaskUtil.findUniqueGenresUpperCase(library);
    System.out.println(uniqueGenresUpperCase);
  }
}

