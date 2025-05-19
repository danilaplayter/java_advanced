package ru.mentee.power.streams;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StreamTransformTaskUtilTest {

  private List<Book> testLibrary;
  private Book bookSciFi2001, bookFantasy1999, bookSciFiComedy2005, bookThriller2010, bookFantasy2020, bookNoGenre2005;

  @BeforeEach
  void setUp() {
    bookSciFi2001 = new Book("SciFi_2001", "Author A", 2001, List.of("Научная фантастика"));
    bookFantasy1999 = new Book("Fantasy_1999", "Author B", 1999, List.of("Фэнтези"));
    bookSciFiComedy2005 = new Book("SciFi_Comedy_2005", "Author C", 2005,
        List.of("Научная фантастика", "Комедия", "фантастика"));
    bookThriller2010 = new Book("Thriller_2010", "Author A", 2010, List.of("Триллер"));
    bookFantasy2020 = new Book("Fantasy_2020", "Author D", 2020, List.of("ФЭНТЕЗИ", "Приключения"));
    bookNoGenre2005 = new Book("No_Genre_Book_2005", "Author E", 2005, List.of());

    testLibrary = List.of(bookSciFi2001, bookFantasy1999, bookSciFiComedy2005, bookThriller2010,
        bookFantasy2020, bookNoGenre2005,
        new Book("Old_SciFi", "Author F", 1980, List.of("фантастика")));
  }

  @Test
  @DisplayName("Задача 1: Поиск книг (Фантастика, >2000г)")
  void findSciFiTitlesAfter2000_ShouldReturnCorrectTitles() {
    List<String> result = StreamTransformTaskUtil.findSciFiTitlesAfter2000(testLibrary);
    assertThat(result).isNotNull();
    assertThat(result).containsExactlyInAnyOrder("SciFi_2001", "SciFi_Comedy_2005");
  }

  @Test
  @DisplayName("Задача 2: Поиск уникальных авторов (отсортированных)")
  void findUniqueAuthors_ShouldReturnSortedUniqueAuthors() {
    List<String> result = StreamTransformTaskUtil.findUniqueAuthors(testLibrary);
    assertThat(result).isNotNull();
    assertThat(result).containsExactly("Author A", "Author B", "Author C", "Author D", "Author E",
        "Author F");
  }

  @Test
  @DisplayName("Задача 3: Поиск уникальных жанров (в верхнем регистре)")
  void findUniqueGenresUpperCase_ShouldReturnUniqueUpperCaseGenres() {
    Set<String> result = StreamTransformTaskUtil.findUniqueGenresUpperCase(testLibrary);
    assertThat(result).isNotNull();
    assertThat(result).containsExactlyInAnyOrder(
        "НАУЧНАЯ ФАНТАСТИКА",
        "ФЭНТЕЗИ",
        "КОМЕДИЯ",
        "ФАНТАСТИКА",
        "ТРИЛЛЕР",
        "ПРИКЛЮЧЕНИЯ"
    );
  }
  @Test
  @DisplayName("Все методы должны корректно обрабатывать пустой список книг")
  void methods_WithEmptyBookList_ShouldReturnEmptyCollections() {
    List<Book> emptyLibrary = List.of();

    assertThat(StreamTransformTaskUtil.findSciFiTitlesAfter2000(emptyLibrary)).isEmpty();
    assertThat(StreamTransformTaskUtil.findUniqueAuthors(emptyLibrary)).isEmpty();
    assertThat(StreamTransformTaskUtil.findUniqueGenresUpperCase(emptyLibrary)).isEmpty();
  }

  @Test
  @DisplayName("Все методы должны корректно обрабатывать null вместо списка книг")
  void methods_WithNullBookList_ShouldReturnEmptyCollections() {
    assertThat(StreamTransformTaskUtil.findSciFiTitlesAfter2000(null)).isEmpty();
    assertThat(StreamTransformTaskUtil.findUniqueAuthors(null)).isEmpty();
    assertThat(StreamTransformTaskUtil.findUniqueGenresUpperCase(null)).isEmpty();
  }
}