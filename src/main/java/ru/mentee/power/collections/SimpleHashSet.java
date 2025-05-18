package ru.mentee.power.collections;

public class SimpleHashSet<E> { // Начнем без интерфейса

  private transient SimpleHashMap<E, Object> map;

  // Заглушка для значений в HashMap
  private static final Object PRESENT = new Object();

  // --- Конструкторы ---
  public SimpleHashSet() {
    this.map = new SimpleHashMap<>();
  }

  public SimpleHashSet(int initialCapacity, float loadFactor) {
    this.map = new SimpleHashMap<>(initialCapacity, loadFactor);
  }

  public SimpleHashSet(int initialCapacity) {
    this.map = new SimpleHashMap<>(initialCapacity);
  }

  // --- Основные методы Set ---

  public int size() {
    return map.size();
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }

  public boolean contains(Object o) {
    return map.containsKey(o);
  }

  public boolean add(E e) {
    return map.put(e, PRESENT) == null;
  }

  public boolean remove(Object o) {
    return map.remove(o) == PRESENT;
  }

  public void clear() {
    map.clear();
  }

  @Override
  public String toString() {
    if (map.isEmpty()) {
      return "[]";
    }

    StringBuilder sb = new StringBuilder("[");
    boolean first = true;

    // Используем внутренний итератор SimpleHashMap для ключей
    for (SimpleHashMap.Node<E, Object> node : map.table) {
      while (node != null) {
        if (!first) {
          sb.append(", ");
        }
        sb.append(node.key);
        first = false;
        node = node.next;
      }
    }

    return sb.append("]").toString();
  }
}