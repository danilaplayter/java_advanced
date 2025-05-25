package ru.mentee.power.solid.lsp.task;

import ru.mentee.power.solid.lsp.task.solution.NotificationException;

interface NotificationStrategy {
  void send(String message, String recipient) throws NotificationException;
}
