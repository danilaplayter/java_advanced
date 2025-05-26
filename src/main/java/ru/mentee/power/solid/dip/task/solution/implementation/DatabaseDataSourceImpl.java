package ru.mentee.power.solid.dip.task.solution.implementation;

import java.util.List;
import ru.mentee.power.solid.dip.task.solution.abstraction.DataSource;

public class DatabaseDataSourceImpl implements DataSource {
  @Override
  public List<String> getData() {
    System.out.println("DIP Solution: Получение данных из реальной БД...");
    return List.of("DB Data 1 (DIP)", "DB Data 2 (DIP)", "DB Data 3 (DIP)");
  }
}
