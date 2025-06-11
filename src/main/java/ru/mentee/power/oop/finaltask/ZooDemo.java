package ru.mentee.power.oop.finaltask;

import ru.mentee.power.oop.finaltask.controller.ZooController;
import ru.mentee.power.oop.finaltask.view.ZooView;

public class ZooDemo {

  public static void main(String[] args) {
    ZooView view = new ZooView();
    ZooController controller = new ZooController(view);
    controller.startSafari();
    controller.performBehaviorChanges();
  }
}