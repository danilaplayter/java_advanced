package ru.mentee.power.patterns.factory;

import ru.mentee.power.patterns.factory.apps.impl.HtmlEditorApp;
import ru.mentee.power.patterns.factory.apps.impl.TextEditorApp;

public class DocumentFactoryDemo {
  public static void main(String[] args) {
    TextEditorApp textEditor = new TextEditorApp();
    HtmlEditorApp htmlEditor = new HtmlEditorApp();

    System.out.println("Работа с текстовым редактором:");
    textEditor.newDocument();

    System.out.println("\nРабота с HTML редактором:");
    htmlEditor.newDocument();
  }
}