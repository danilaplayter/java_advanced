package ru.mentee.power.patterns.strategy.impl;

import ru.mentee.power.patterns.strategy.Order;
import ru.mentee.power.patterns.strategy.ShippingStrategy;

public class RegionBasedShipping implements ShippingStrategy {

  @Override
  public double calculate(Order order) {

    switch (order.getDestinationRegion()){
      case "Центр": return 5.0;
      case "Север": return 15.0;
      default: return 12.0;
    }
  }
}