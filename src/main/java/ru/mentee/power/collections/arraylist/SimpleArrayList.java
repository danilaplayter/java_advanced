package ru.mentee.power.collections.arraylist;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class SimpleArrayList<E> {

  private static final int DEFAULT_CAPACITY = 10;
  private static final Object[] EMPTY_ELEMENTDATA = {}; // Для оптимизации

  private Object[] elementData;
  private int size;

  // --- Конструкторы ---
  public SimpleArrayList() {
    this.elementData = new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  public SimpleArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
      this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
      this.elementData = EMPTY_ELEMENTDATA;
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }
    this.size = 0;
  }

  // --- Основные методы ---

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  public int indexOf(Object o) {
    for(int i = 0; i < size; i++){
      if(Objects.equals(o, elementData[i])){
        return i;
      }
    }
    return -1;
  }

  public int lastIndexOf(Object o) {
    for(int i = size - 1; i >= 0; i--) {
      if(Objects.equals(o, elementData[i])) {
        return i;
      }
    }
    return -1;
  }

  public E get(int index) {
    rangeCheck(index);
    return elementData(index);
  }

  public E set(int index, E element) {
    rangeCheck(index);
    E oldValue = elementData(index);
    elementData[index] = element;
    return oldValue;
  }

  public boolean add(E element) {
    ensureCapacityInternal(size + 1);
    elementData[size++] = element;
    return true;
  }

  public void add(int index, E element) {
    rangeCheckForAdd(index);
    ensureCapacityInternal(size + 1);
    System.arraycopy(elementData, index, elementData, index + 1, size - index);
    elementData[index] = element;
    size++;
  }

  public E remove(int index) {
    rangeCheck(index);
    E oldValue = elementData(index);
    int numMoved = size - index - 1;
    if (numMoved > 0) {
      System.arraycopy(elementData, index + 1, elementData, index, numMoved);
    }
    elementData[--size] = null;
    return oldValue;
  }

  public boolean remove(Object o) {
    int index = indexOf(o);
    if (index >= 0) {
      remove(index);
      return true;
    }
    return false;
  }

  public void clear() {
    for (int i = 0; i < size; i++) {
      elementData[i] = null;
    }
    size = 0;
  }

  public Object[] toArray() {
    return Arrays.copyOf(elementData, size);
  }

  // --- Вспомогательные методы ---

  @SuppressWarnings("unchecked")
  private E elementData(int index) {
    return (E) elementData[index];
  }

  private void rangeCheck(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  private void rangeCheckForAdd(int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  private void ensureCapacityInternal(int minCapacity) {
    if (minCapacity > elementData.length) {
      grow(minCapacity);
    }
  }

  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8; // Ограничение

  private void grow(int minCapacity) {
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity < minCapacity) {
      newCapacity = minCapacity;
    }
    if (newCapacity > MAX_ARRAY_SIZE) {
      newCapacity = hugeCapacity(minCapacity);
    }
    elementData = Arrays.copyOf(elementData, newCapacity);
  }

  private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) // overflow
      throw new OutOfMemoryError();
    return (minCapacity > MAX_ARRAY_SIZE) ?
        Integer.MAX_VALUE :
        MAX_ARRAY_SIZE;
  }

  @Override
  public String toString() {
    if (size == 0) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (int i = 0; i < size; i++) {
      sb.append(elementData[i]);
      if (i == size - 1) {
        return sb.append(']').toString();
      }
      sb.append(", ");
    }
    return sb.append(']').toString();
  }

}