package ru.mentee.power.solid.dip.task.solution.implementation;

import ru.mentee.power.solid.dip.task.solution.abstraction.ReportFormatter;
import java.util.List;

public class HtmlReportFormatterImpl implements ReportFormatter {
  @Override
  public String format(List<String> data) {
    StringBuilder htmlBuilder = new StringBuilder();
    htmlBuilder.append("<html><head><title>Report</title></head><body>");
    htmlBuilder.append("<h1>Report</h1><ul>");

    for (String item : data) {
      htmlBuilder.append("<li>").append(item).append("</li>");
    }

    htmlBuilder.append("</ul></body></html>");
    return htmlBuilder.toString();
  }
}