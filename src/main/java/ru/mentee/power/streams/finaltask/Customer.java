package ru.mentee.power.streams.finaltask;

import java.util.Objects;

public class Customer {
  private final String id;
  private final String name;
  private final String city;

  public Customer(String id, String name, String city) {
    this.id = Objects.requireNonNull(id, "Customer ID cannot be null");
    this.name = Objects.requireNonNull(name, "Customer name cannot be null");
    this.city = Objects.requireNonNull(city, "Customer city cannot be null");
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return id.equals(customer.id); // Сравнение только по ID
  }

  @Override
  public int hashCode() {
    return Objects.hash(id); // Хеш-код только по ID
  }

  @Override
  public String toString() {
    return "Customer{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", city='" + city + '\'' +
        '}';
  }
}