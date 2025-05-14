package ru.mentee.power.exceptions.multilevel;

public class FileReadException extends FileOperationException {
  private final String filePath;

  public FileReadException(String filePath){
    super("Ошибка чтения файла: " + filePath);
    this.filePath = filePath;
  }

  public FileReadException(String filePath, Throwable cause){
    super("Ошибка чтения файла: " + filePath, cause);
    this.filePath = filePath;
  }

  public String getFilePath(){
    return filePath;
  }

}
