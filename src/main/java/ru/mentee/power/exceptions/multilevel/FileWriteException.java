package ru.mentee.power.exceptions.multilevel;

public class FileWriteException extends FileOperationException {

  private final String filePath;

  public FileWriteException(String filePath) {
    super("Ошибка записи в файл: " + filePath);
    this.filePath = filePath;
  }

  public FileWriteException(String filePath, Throwable cause) {
    super("Ошибка записи в файл: " + filePath, cause);
    this.filePath = filePath;
  }

  public String getFilePath() {
    return filePath;
  }

}
