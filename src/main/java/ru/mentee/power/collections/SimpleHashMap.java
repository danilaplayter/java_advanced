package ru.mentee.power.collections;

import java.util.Objects;

public class SimpleHashMap<K, V> { // implements Map<K, V> - позже

  private static final int DEFAULT_INITIAL_CAPACITY = 16;
  private static final float DEFAULT_LOAD_FACTOR = 0.75f;
  private static final int MAXIMUM_CAPACITY = 1 << 30; // Макс. ёмкость

  Node<K, V>[] table;
  private int size;
  private int threshold;
  private final float loadFactor;

  // --- Конструкторы ---
  public SimpleHashMap() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  public SimpleHashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
  }

  @SuppressWarnings("unchecked")
  public SimpleHashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
    }
    if (initialCapacity > MAXIMUM_CAPACITY) {
      initialCapacity = MAXIMUM_CAPACITY;
    }
    if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
      throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
    }

    this.loadFactor = loadFactor;
    int capacity = tableSizeFor(initialCapacity);
    this.threshold = calculateThreshold(capacity, loadFactor);
    this.table = (Node<K, V>[]) new Node[capacity];
  }

  // --- Внутренний класс Node ---
  static class Node<K, V> { // implements Map.Entry<K, V>

    final int hash;
    final K key;
    V value;
    Node<K, V> next; // Ссылка на следующий узел в цепочке (при коллизии)

    Node(int hash, K key, V value, Node<K, V> next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
    }

    public int getHash() {
      return hash;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }

    public Node<K, V> getNext() {
      return next;
    }

    public void setNext(Node<K, V> next) {
      this.next = next;
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Node<?, ?> node = (Node<?, ?>) o;
      return hash == node.hash && Objects.equals(key, node.key) && Objects.equals(value, node.value)
          && Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
      return Objects.hash(hash, key, value, next);
    }

    @Override
    public String toString() {
      return "Node{" + "hash=" + hash + ", key=" + key + ", value=" + value + ", next=" + next
          + '}';
    }
  }

  // --- Основные методы Map ---

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public V get(Object key) {
    int hash = hash(key);
    int index = indexFor(hash, table.length);

    Node<K, V> node = table[index];

    while (node != null) {
      if (node.hash == hash && (Objects.equals(key, node.key))) {
        return node.value;
      }
      node = node.next;
    }
    return null;
  }

  public boolean containsKey(Object key) {
    int hash = hash(key);
    int index = indexFor(hash, table.length);
    Node<K, V> node = table[index];
    while (node != null) {
      if (node.hash == hash && (Objects.equals(key, node.key))) {
        return true;
      }
      node = node.next;
    }
    return false;
  }

  public V put(K key, V value) {
    int hash = hash(key);
    int index = indexFor(hash, table.length);

    Node<K, V> node = table[index];
    while (node != null) {
      if (node.hash == hash && (Objects.equals(key, node.key))) {
        V oldValue = node.value;
        node.value = value;
        return oldValue;
      }
      node = node.next;
    }

    Node<K, V> newNode = new Node<>(hash, key, value, table[index]);
    table[index] = newNode;

    if (size++ > threshold) {
      resize();
    }
    return null;

  }

  public V remove(Object key) {
    int hash = hash(key);
    int index = indexFor(hash, table.length);

    Node<K, V> node = table[index];
    Node<K, V> previous = null;

    while (node != null) {
      if (node.hash == hash && (Objects.equals(key, node.key))) {
        if (previous == null) {
          table[index] = node.next;
        } else {
          previous.next = node.next;

        }
        size--;
        return node.value;
      }
      previous = node;
      node = node.next;
    }
    return null;
  }

  public void clear() {
    for(int i = 0; i < table.length; i++){
      table[i] = null;
    }
    size = 0;
  }

  // --- Вспомогательные методы ---

  // Простой расчет хеша (в реальном HashMap он сложнее для распределения)
  static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }

  // Определение индекса корзины
  static int indexFor(int h, int length) {
    // Используем битовую операцию И, так как length всегда степень двойки
    return h & (length - 1);
  }

  // Расчет порога для расширения
  static int calculateThreshold(int capacity, float loadFactor) {
    return (int) (capacity * loadFactor);
  }

  // Нахождение ближайшей степени двойки >= cap
  static final int tableSizeFor(int cap) {
    int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }

  @SuppressWarnings("unchecked")
  void resize() {
    Node<K, V>[] oldTable = table;
    int oldCapacity = oldTable.length;

    if(oldCapacity >= MAXIMUM_CAPACITY){
      threshold = Integer.MAX_VALUE;
      return;
    }

    int newCapacity = oldCapacity*2;
    threshold = calculateThreshold(newCapacity, loadFactor);

    Node<K, V>[] newTable = new Node[newCapacity];

    for (int i = 0; i < oldCapacity; i++) {
      Node<K, V> node = oldTable[i];
      if (node != null) {
        oldTable[i] = null; // help GC

        do {
          Node<K, V> next = node.next;
          int newIndex = indexFor(node.hash, newCapacity);
          node.next = newTable[newIndex];
          newTable[newIndex] = node;
          node = next;
        } while (node != null);
      }
    }

    table = newTable;

  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return "{}";
    }

    StringBuilder sb = new StringBuilder(size * 16);
    sb.append("{");

    for (Node<K, V> node : table) {
      for (; node != null; node = node.next) {
        sb.append(node.key).append("=").append(node.value).append(", ");
      }
    }

    sb.setLength(sb.length() - 2);
    return sb.append("}").toString();
  }
}