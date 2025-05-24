package ru.mentee.power.solid.srp.task;

import java.util.List;
import java.util.stream.Collectors;

public class ReportData {
  private String name;
  private double value;

  public ReportData(String name, double value) { this.name = name; this.value = value; }
  public String getName() { return name; }
  public double getValue() { return value; }

  @Override
  public String toString() {
    return "ReportData{" +
        "name='" + name + '\'' +
        ", value=" + value +
        '}';
  }
}