package ru.mentee.power.oop.finaltask.animal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.oop.finaltask.behavior.MoveBehavior;
import ru.mentee.power.oop.finaltask.behavior.SoundBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Тесты полиморфизма и смены поведения животных")
class AnimalPolymorphismTest {

  private Lion testLion;
  private Snake testSnake;
  private MoveBehavior mockMoveBehavior;
  private SoundBehavior mockSoundBehavior;

  @BeforeEach
  void setUp() {
    testLion = new Lion("Симба", 5, "коричневый");
    testSnake = new Snake("Каа", 10, true);
    mockMoveBehavior = mock(MoveBehavior.class);
    mockSoundBehavior = mock(SoundBehavior.class);
  }

  @Test
  @DisplayName("performMove должен вызывать правильную стратегию движения")
  void performMoveShouldDelegateToCorrectStrategy() {
    testLion.performMove();
    assertThat(testLion.getMoveBehavior()).isInstanceOf(WalkBehavior.class);

    testSnake.performMove();
    assertThat(testSnake.getMoveBehavior()).isInstanceOf(SlitherBehavior.class);
  }

  @Test
  @DisplayName("performSound должен вызывать правильную стратегию звука")
  void performSoundShouldDelegateToCorrectStrategy() {
    testLion.performSound();
    assertThat(testLion.getSoundBehavior()).isInstanceOf(RoarBehavior.class);

    testSnake.performSound();
    assertThat(testSnake.getSoundBehavior()).isInstanceOf(HissBehavior.class);
  }

  @Test
  @DisplayName("setMoveBehavior должен изменять стратегию движения")
  void setMoveBehaviorShouldChangeStrategy() {
    testLion.setMoveBehavior(mockMoveBehavior);
    testLion.performMove();

    verify(mockMoveBehavior, times(1)).move();

    testLion.setMoveBehavior(new FlyBehavior());
    testLion.performMove();
    assertThat(testLion.getMoveBehavior()).isInstanceOf(FlyBehavior.class);
  }

  @Test
  @DisplayName("setSoundBehavior должен изменять стратегию звука")
  void setSoundBehaviorShouldChangeStrategy() {
    testSnake.setSoundBehavior(mockSoundBehavior);
    testSnake.performSound();

    verify(mockSoundBehavior, times(1)).sound();

    testSnake.setSoundBehavior(new ChirpBehavior());
    testSnake.performSound();
    assertThat(testSnake.getSoundBehavior()).isInstanceOf(ChirpBehavior.class);
  }

  private MoveBehavior getMoveBehavior(Animal animal) {
    return animal.moveBehavior;
  }

  private SoundBehavior getSoundBehavior(Animal animal) {
    return animal.soundBehavior;
  }
}