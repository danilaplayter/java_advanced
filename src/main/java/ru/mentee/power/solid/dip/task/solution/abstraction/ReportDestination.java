package ru.mentee.power.solid.dip.task.solution.abstraction;

public interface ReportDestination {

  void write(String content, String destinationHint);

}