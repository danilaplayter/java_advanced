package ru.mentee.power.finaltask.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

  private String ticker;
  private BigDecimal price;
  private LocalDateTime lastUpdate;

}
