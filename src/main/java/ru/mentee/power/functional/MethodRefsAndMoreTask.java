package ru.mentee.power.functional;

import java.util.function.*;

public class MethodRefsAndMoreTask {

  public static void main(String[] args) {

    // --- Задача 1: Ссылки на методы ---
    System.out.println("--- Ссылки на методы ---");

    // 1.1: Function<String, Integer> через ссылку на String::length
    Function<String, Integer> getStringLength = MethodRefsUtil.getStringLengthFunction();
    System.out.println("Длина 'Java': " + getStringLength.apply("Java"));

    // 1.2: Consumer<String> через ссылку на System.out::println
    Consumer<String> printString = MethodRefsUtil.getPrintStringConsumer();
    printString.accept("Печать этой строки через ссылку.");

    // 1.3: Supplier<Thread> через ссылку на конструктор Thread::new
    Supplier<Thread> createThread = MethodRefsUtil.getThreadSupplier();
    Thread t = createThread.get();
    System.out.println("Создан поток: " + t.getName());


    // --- Задача 2: BiFunction ---
    System.out.println("\n--- BiFunction: Сумма чисел в строку ---");
    BiFunction<Integer, Integer, String> formatSum = MethodRefsUtil.getFormatSumFunction();
    System.out.println(formatSum.apply(5, 7)); // Ожидается "5 + 7 = 12"
    System.out.println(formatSum.apply(-1, 10));

    // --- Задача 3: IntBinaryOperator ---
    System.out.println("\n--- IntBinaryOperator: Максимум двух int ---");
    IntBinaryOperator findMax = MethodRefsUtil.getMaxOperator();
    System.out.println("Максимум из 10 и 15: " + findMax.applyAsInt(10, 15)); // Ожидается 15
    System.out.println("Максимум из -5 и -2: " + findMax.applyAsInt(-5, -2));

    // --- Задача 4: ToIntFunction ---
    System.out.println("\n--- ToIntFunction: Длина строки в int ---");
    ToIntFunction<String> stringToIntLength = MethodRefsUtil.getStringToIntLengthFunction();
    System.out.println("Длина 'Function': " + stringToIntLength.applyAsInt("Function")); // Ожидается 8
    System.out.println("Длина '': " + stringToIntLength.applyAsInt(""));
  }
}