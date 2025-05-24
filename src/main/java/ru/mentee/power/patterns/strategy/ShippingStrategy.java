package ru.mentee.power.patterns.strategy;

@FunctionalInterface
public interface ShippingStrategy {
  double calculate(Order order);
}