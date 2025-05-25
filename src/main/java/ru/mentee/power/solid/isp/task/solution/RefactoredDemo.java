package ru.mentee.power.solid.isp.task.solution;

import ru.mentee.power.solid.isp.task.solution.client.RefactoredPrintClient;
import ru.mentee.power.solid.isp.task.solution.client.RefactoredScanClient;
import ru.mentee.power.solid.isp.task.solution.device.RefactoredOfficeMFU;
import ru.mentee.power.solid.isp.task.solution.device.RefactoredSimplePrinter;

public class RefactoredDemo {
  public static void main(String[] args) {
    RefactoredSimplePrinter simplePrinter = new RefactoredSimplePrinter();
    RefactoredOfficeMFU officeMFU = new RefactoredOfficeMFU();

    RefactoredPrintClient printClient = new RefactoredPrintClient();
    RefactoredScanClient scanClient = new RefactoredScanClient();

    System.out.println("--- Тестирование PrintClient ---");
    printClient.sendToPrint(simplePrinter, "Простой документ на печать.");
    printClient.sendToPrint(officeMFU, "Документ для МФУ на печать.");

    System.out.println("\n--- Тестирование ScanClient ---");
    scanClient.scanDocument(officeMFU, "Документ для сканирования на МФУ.");

  }
}