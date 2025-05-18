package ru.mentee.power.collections.stacksqueues;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class TaskScheduler {

  private static final Random random = new Random();

  public static void simulateTaskProcessing(List<String> tasks, int maxConcurrent) {
    if (tasks == null || maxConcurrent <= 0) {
      System.out.println("Некорректные входные данные для симуляции.");
      return;
    }

    Queue<String> waitingQueue = new LinkedList<>(tasks);
    Set<String> activeTasks = new HashSet<>();

    System.out.println(
        "--- Начало симуляции обработки задач (Макс. параллельно: " + maxConcurrent + ") ---");

    while (!waitingQueue.isEmpty() || !activeTasks.isEmpty()) {
      while (activeTasks.size() < maxConcurrent && !waitingQueue.isEmpty()) {
        String task = waitingQueue.poll();
        activeTasks.add(task);
        System.out.println("Начало обработки: " + task);
      }

      // Симулируем завершение задач
      if (!activeTasks.isEmpty()) {
        String[] activeArray = activeTasks.toArray(new String[0]);
        String completedTask = activeArray[random.nextInt(activeArray.length)];

        activeTasks.remove(completedTask);
        System.out.println("Завершена обработка: " + completedTask);

        try {
          Thread.sleep(100 + random.nextInt(200));
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          return;
        }
      }
    }

    System.out.println("--- Симуляция обработки задач завершена ---");
  }
}