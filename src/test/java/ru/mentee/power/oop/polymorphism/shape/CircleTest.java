package ru.mentee.power.oop.polymorphism.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для класса Circle")
class CircleTest {

  @Test
  @DisplayName("getArea должен вычислять площадь круга")
  void getAreaShouldCalculateCorrectly() {
    // Arrange
    Circle circle = new Circle("Red", 5.0);
    double expectedArea = Math.PI * 5.0 * 5.0;

    // Act
    double actualArea = circle.getArea();

    // Assert
    assertThat(actualArea).isEqualTo(expectedArea);
  }

  @Test
  @DisplayName("getPerimeter должен вычислять длину окружности")
  void getPerimeterShouldCalculateCorrectly() {
    // Arrange
    Circle circle = new Circle("Blue", 3.0);
    double expectedPerimeter = 2 * Math.PI * 3.0;

    // Act
    double actualPerimeter = circle.getPerimeter();

    // Assert
    assertThat(actualPerimeter).isEqualTo(expectedPerimeter);
  }

  @ParameterizedTest
  @ValueSource(doubles = {0.0, -1.0, -0.0001})
  @DisplayName("Конструктор должен выбрасывать исключение при не положительном радиусе")
  void constructorShouldThrowExceptionForNonPositiveRadius(double invalidRadius) {
    // Act & Assert
    assertThatThrownBy(() -> new Circle("Green", invalidRadius))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Radius must be positive");
  }

  @Test
  @DisplayName("Конструктор должен корректно работать с положительным радиусом")
  void constructorShouldWorkWithPositiveRadius() {
    // Arrange
    double validRadius = 1.0;

    // Act & Assert
    assertThatCode(() -> new Circle("Yellow", validRadius))
        .doesNotThrowAnyException();
  }
}