package ru.mentee.power.patterns.factory.apps;

import ru.mentee.power.patterns.factory.documents.Document;

abstract public class Application {

  public void newDocument() {
    Document doc = createDocument();
    doc.open();
    System.out.println("Открыт новый документ в приложении.");
  }

  protected abstract Document createDocument();
}
