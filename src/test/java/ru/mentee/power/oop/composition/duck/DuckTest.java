package ru.mentee.power.oop.composition.duck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.oop.composition.behavior.FlyBehavior;
import ru.mentee.power.oop.composition.behavior.QuackBehavior;
import ru.mentee.power.oop.composition.behavior.impl.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для Уток и их Поведения")
class DuckTest {
  private Duck mallardDuck;
  private Duck modelDuck;
  private Duck rubberDuck;
  private Duck decoyDuck;

  @BeforeEach
  void setUp() {
    mallardDuck = new MallardDuck();
    modelDuck = new ModelDuck();
    rubberDuck = new RubberDuck();
    decoyDuck = new DecoyDuck();
  }

  @Test
  @DisplayName("MallardDuck должна использовать FlyWithWings и Quack")
  void mallardDuckShouldUseCorrectBehaviors() {
    assertThat(mallardDuck.flyBehavior).isInstanceOf(FlyWithWings.class);
    assertThat(mallardDuck.quackBehavior).isInstanceOf(Quack.class);
  }

  @Test
  @DisplayName("ModelDuck должна изначально использовать FlyNoWay")
  void modelDuckShouldInitiallyNotFly() {
    assertThat(modelDuck.flyBehavior).isInstanceOf(FlyNoWay.class);
  }

  @Test
  @DisplayName("Должна быть возможность динамически менять поведение полета")
  void shouldAllowDynamicChangeOfFlyBehavior() {
    FlyBehavior newBehavior = new FlyRocketPowered();
    modelDuck.setFlyBehavior(newBehavior);
    assertThat(modelDuck.flyBehavior).isSameAs(newBehavior);
  }

  @Test
  @DisplayName("Должна быть возможность динамически менять поведение кряканья")
  void shouldAllowDynamicChangeOfQuackBehavior() {
    QuackBehavior newBehavior = new MuteQuack();
    modelDuck.setQuackBehavior(newBehavior);
    assertThat(modelDuck.quackBehavior).isSameAs(newBehavior);
  }

  @Test
  @DisplayName("RubberDuck должна пищать и не летать")
  void rubberDuckShouldSqueakAndNotFly() {
    assertThat(rubberDuck.flyBehavior).isInstanceOf(FlyNoWay.class);
    assertThat(rubberDuck.quackBehavior).isInstanceOf(Squeak.class);
  }

  @Test
  @DisplayName("DecoyDuck должна быть тихой и не летать")
  void decoyDuckShouldBeSilentAndNotFly() {
    assertThat(decoyDuck.flyBehavior).isInstanceOf(FlyNoWay.class);
    assertThat(decoyDuck.quackBehavior).isInstanceOf(MuteQuack.class);
  }
}