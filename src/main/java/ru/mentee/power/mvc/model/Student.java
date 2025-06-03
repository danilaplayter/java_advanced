package ru.mentee.power.mvc.model;
import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Student {
  private long id;
  private String name;
}