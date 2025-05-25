package ru.mentee.power.patterns.factory.apps.impl;

import ru.mentee.power.patterns.factory.apps.Application;
import ru.mentee.power.patterns.factory.documents.Document;
import ru.mentee.power.patterns.factory.documents.impl.TextDocument;

public class TextEditorApp extends Application {

  @Override
  protected Document createDocument() {
    return new TextDocument();
  }
}
