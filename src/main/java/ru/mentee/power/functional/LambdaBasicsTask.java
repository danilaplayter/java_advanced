package ru.mentee.power.functional;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaBasicsTask {

  public static void main(String[] args) {

    // --- Задача 1: Predicate ---
    System.out.println("--- Predicate: Строка начинается с 'A'? ---");
    // Получаем предикат из утилитного класса
    Predicate<String> startsWithA = LambdaBasicsTaskUtil.createStartsWithAPredicate();

    // Используем Predicate (предполагаем, что он реализован в Util классе)
    System.out.println("Apple: " + startsWithA.test("Apple"));
    System.out.println("Banana: " + startsWithA.test("Banana"));
    System.out.println("Avocado: " + startsWithA.test("Avocado"));
    System.out.println("null: " + startsWithA.test(null));
    System.out.println("пусто: " + startsWithA.test(""));

    // --- Задача 2: Function ---
    System.out.println("\n--- Function: Число положительное? ---");
    // Получаем функцию из утилитного класса
    Function<Integer, Boolean> isPositive = LambdaBasicsTaskUtil.createIsPositiveFunction();

    // Используем Function
    System.out.println("5 > 0? " + isPositive.apply(5));
    System.out.println("-10 > 0? " + isPositive.apply(-10));
    System.out.println("0 > 0? " + isPositive.apply(0));


    // --- Задача 3: Consumer ---
    System.out.println("\n--- Consumer: Печать элементов списка ---");
    List<String> names = List.of("Иван", "Мария", "Петр");
    // Получаем консьюмер из утилитного класса
    Consumer<List<String>> printListElements = LambdaBasicsTaskUtil.createPrintListElementsConsumer();

    // Используем Consumer
    printListElements.accept(names);


    // --- Задача 4: Supplier ---
    System.out.println("\n--- Supplier: Случайное число 0-99 ---");
    // Получаем поставщик из утилитного класса
    Supplier<Integer> randomIntSupplier = LambdaBasicsTaskUtil.createRandomIntSupplier();

    // Используем Supplier
    System.out.println("Случайное 1: " + randomIntSupplier.get());
    System.out.println("Случайное 2: " + randomIntSupplier.get());

  }
}