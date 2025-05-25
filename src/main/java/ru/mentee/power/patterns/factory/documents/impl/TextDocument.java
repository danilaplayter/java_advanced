package ru.mentee.power.patterns.factory.documents.impl;

import ru.mentee.power.patterns.factory.documents.Document;

public class TextDocument implements Document {

  @Override
  public void open() {
    System.out.println("Открыт текстовый документ");
  }

  @Override
  public void save() {
    System.out.println("Текстовый документ сохранен");
  }

  @Override
  public void close() {
    System.out.println("Текстовый документ закрыт");
  }
}
