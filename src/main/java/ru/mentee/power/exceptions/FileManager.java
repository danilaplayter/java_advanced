package ru.mentee.power.exceptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Менеджер файлов с продвинутой обработкой исключений.
 */
public class FileManager {

  private static final Logger logger = Logger.getLogger(FileManager.class.getName());
  private static final long MIN_FREE_SPACE = 1024 * 1024; // 1MB минимум свободного места

  public String readTextFile(String filePath) throws FileProcessingException, FileLockedException {
    File file = new File(filePath);
    if (!file.exists()) {
      throw new FileProcessingException("Файл не существует: " + filePath);
    }

    if (isFileLocked(filePath)) {
      String owner = getLockOwner(filePath);
      throw new FileLockedException(filePath, owner);
    }


    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append(System.lineSeparator());
      }
      return content.toString().trim();
    } catch (IOException e) {
      String errorMsg = "Ошибка чтения файла: " + filePath;
      logger.log(Level.SEVERE, errorMsg, e);
      throw new FileProcessingException(errorMsg, e);
    }
  }


  public void writeTextFile(String filePath, String content)
      throws FileProcessingException, FileLockedException, StorageFullException {
    File file = new File(filePath);
    if (file.exists() && isFileLocked(filePath)) {
      String owner = getLockOwner(filePath);
      throw new FileLockedException(filePath, owner);
    }

    try {
      checkFreeSpace(file.getParent(), content.getBytes(StandardCharsets.UTF_8).length);
      Files.createDirectories(file.toPath().getParent());
      Files.write(file.toPath(), content.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw new FileProcessingException("Ошибка записи в файл: " + filePath, e);
    }
  }

  public void copyFile(String sourcePath, String destinationPath)
      throws FileProcessingException, FileLockedException, StorageFullException {
    if (isFileLocked(sourcePath)) {
      String owner = getLockOwner(sourcePath);
      String errorMsg = "Исходный файл занят другим процессом: " + owner;
      logger.log(Level.SEVERE, errorMsg);
      throw new FileLockedException(sourcePath, owner);
    }

    File destFile = new File(destinationPath);
    if (destFile.exists() && isFileLocked(destinationPath)) {
      String owner = getLockOwner(destinationPath);
      String errorMsg = "Назначенный файл занят другим процессом: " + owner;
      logger.log(Level.SEVERE, errorMsg);
      throw new FileLockedException(sourcePath, owner);
    }

    try {
      long fileSize = Files.size(Paths.get(sourcePath));
      checkFreeSpace(destFile.getParent(), fileSize);

      Files.copy(Paths.get(sourcePath), Paths.get(destinationPath),
          StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      String errorMsg = "Ошибка копирования файла из " + sourcePath + " в " + destinationPath;
      logger.log(Level.SEVERE, errorMsg, e);
      throw new FileProcessingException(errorMsg, e);
    }
  }

  public Properties loadProperties(String filePath) throws FileProcessingException, InvalidFileFormatException {
    if (!filePath.toLowerCase().endsWith(".properties")) {
      String errorMsg = "Неверный формат строки. Ожидался .properties файл: " + filePath;
      logger.log(Level.SEVERE, errorMsg);
      throw new InvalidFileFormatException(filePath, errorMsg, ".properties");
    }

    Properties properties = new Properties();
    try (InputStream input = new FileInputStream(filePath)) {
      properties.load(input);
      return properties;
    } catch (IOException e) {
      String errorMsg = "Ошибка загрузки характеристик из файла: " + filePath;
      logger.log(Level.SEVERE, errorMsg, e);
      throw new FileProcessingException(errorMsg, e);
    }
  }

  private boolean isFileLocked(String filePath) throws FileLockedException {
    try (RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        FileChannel channel = file.getChannel()) {

      FileLock lock = channel.tryLock();
      if (lock != null) {
        lock.release();
        return false;
      }
      String owner = getLockOwner(filePath);
      throw new FileLockedException(filePath, owner);

    } catch (OverlappingFileLockException e) {
      String owner = getLockOwner(filePath);
      throw new FileLockedException(filePath, owner);
    } catch (Exception e) {
      logger.log(Level.WARNING, "Ошибка проверки состояния файла: " + filePath, e);
      String owner = getLockOwner(filePath);
      throw new FileLockedException(filePath, owner);
    }
  }

  private void checkFreeSpace(String directoryPath, long requiredSpace) throws StorageFullException {
    try {
      Path path = Paths.get(directoryPath);
      FileStore store = Files.getFileStore(path);
      long freeSpace = store.getUsableSpace();

      if (freeSpace < requiredSpace + MIN_FREE_SPACE) {
        String errorMsg = String.format("Недостаточно свободного места. Необходимо: %d, Доступно: %d",
            requiredSpace + MIN_FREE_SPACE, freeSpace);
        logger.log(Level.SEVERE, errorMsg);
        throw new StorageFullException(requiredSpace, MIN_FREE_SPACE);
      }
    } catch (IOException e) {
      String errorMsg = "Ошибка проверки свободного места в директории: " + directoryPath;
      logger.log(Level.SEVERE, errorMsg, e);
      throw new StorageFullException(requiredSpace, MIN_FREE_SPACE);
    }
  }

  private String getLockOwner(String filePath) {
    return "Unknown Process";
  }
}