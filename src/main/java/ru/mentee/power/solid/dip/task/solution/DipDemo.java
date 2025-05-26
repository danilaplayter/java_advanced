package ru.mentee.power.solid.dip.task.solution;
import ru.mentee.power.solid.dip.task.solution.abstraction.*;
import ru.mentee.power.solid.dip.task.solution.implementation.*;
import ru.mentee.power.solid.dip.task.solution.service.ReportService;

public class DipDemo {
  public static void main(String[] args) {
    DataSource dbDataSource = new DatabaseDataSourceImpl();
    ReportFormatter htmlFormatter = new HtmlReportFormatterImpl();
    ReportDestination fileDestination = new FileReportDestinationImpl();

    ReportService reportService = new ReportService(dbDataSource, htmlFormatter, fileDestination);
    reportService.generateAndWriteReport("dip_report.html");

  }
}