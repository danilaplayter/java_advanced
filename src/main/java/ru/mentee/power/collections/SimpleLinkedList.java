package ru.mentee.power.collections;

import java.util.NoSuchElementException;

public class SimpleLinkedList<E> { // Начнем без интерфейсов

  private int size = 0;
  private Node<E> first;
  private Node<E> last;

  // --- Конструктор ---
  public SimpleLinkedList() {
  }

  // --- Внутренний класс Node ---
  private static class Node<E> {

    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }

  // --- Основные методы List ---

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean add(E e) {
    linkLast(e);
    return true;
  }

  public E get(int index) {
    checkElementIndex(index);
    return node(index).item;
  }

  public E remove(int index) {
    checkElementIndex(index);
    return unlinkFirst(node(index));
  }

  public boolean remove(Object o) {
    if (o == null) {
      for (Node<E> x = first; x != null; x = x.next) {
        if (x.item == null) {
          unlink(x);
          return true;
        }
      }
    } else {
      for (Node<E> x = first; x != null; x = x.next) {
        if (o.equals(x.item)) {
          unlink(x);
          return true;
        }
      }
    }
    return false;
  }

  public boolean contains(Object o) {
    Node<E> current = first;

    if (o == null) {
      while (current != null) {
        if (current.item == null) {
          return true;
        }
        current = current.next;
      }
    } else {
      while (current != null) {
        if (o.equals(current.item)) {
          return true;
        }
        current = current.next;
      }
    }

    return false;
  }


  public void clear() {
    for (Node<E> x = first; x != null; ) {
      Node<E> next = x.next;
      x.item = null;
      x.next = null;
      x.prev = null;
      x = next;
    }
    first = last = null;
    size = 0;
  }

  public void addFirst(E e) {
    linkFirst(e);
  }

  public void addLast(E e) {
    linkLast(e);
  }

  public E getFirst() {
    final Node<E> f = first;
    if (f == null) {
      throw new NoSuchElementException();
    }
    return f.item;
  }

  public E getLast() {
    final Node<E> l = last;
    if (l == null) {
      throw new NoSuchElementException();
    }
    return l.item;
  }

  public E removeFirst() {
    final Node<E> f = first;
    if (f == null) {
      throw new NoSuchElementException();
    }
    return unlinkFirst(f);
  }

  public E removeLast() {
    final Node<E> l = last;
    if (l == null) {
      throw new NoSuchElementException();
    }
    return unlinkLast(l);
  }

  // --- Вспомогательные методы ---

  private void linkFirst(E e) {
    final Node<E> f = first;
    final Node<E> newNode = new Node<>(null, e, f);
    first = newNode;
    if (f == null) {
      last = newNode;
    } else {
      f.prev = newNode;
    }
    size++;
  }

  private void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null) {
      first = newNode;
    } else {
      l.next = newNode;
    }
    size++;
  }

  private E unlinkFirst(Node<E> f) {
    final E element = f.item;
    final Node<E> next = f.next;
    f.item = null;
    f.next = null;
    first = next;
    if (next == null) {
      last = null;
    } else {
      next.prev = null;
    }
    size--;
    return element;
  }

  private E unlinkLast(Node<E> l) {
    final E element = l.item;
    final Node<E> prev = l.prev;
    l.item = null;
    l.prev = null;
    last = prev;
    if (prev == null) {
      first = null;
    } else {
      prev.next = null;
    }
    size--;
    return element;
  }

  private E unlink(Node<E> x) {
    final E element = x.item;
    final Node<E> next = x.next;
    final Node<E> prev = x.prev;

    if (prev == null) {
      first = next;
    } else {
      prev.next = next;
      x.prev = null;
    }

    if (next == null) {
      last = prev;
    } else {
      next.prev = prev;
      x.next = null;
    }

    x.item = null;
    size--;
    return element;
  }

  private Node<E> node(int index) {
    if (index < (size >> 1)) {
      Node<E> x = first;
      for (int i = 0; i < index; i++) {
        x = x.next;
      }
      return x;
    } else {
      Node<E> x = last;
      for (int i = size - 1; i > index; i--) {
        x = x.prev;
      }
      return x;
    }
  }

  private boolean isElementIndex(int index) {
    return index >= 0 && index < size;
  }

  private void checkElementIndex(int index) {
    if (!isElementIndex(index)) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (Node<E> x = first; x != null; x = x.next) {
      sb.append(x.item == this ? "(this Collection)" : x.item);
      if (x.next != null) {
        sb.append(", ");
      }
    }
    sb.append(']');
    return sb.toString();
  }
}