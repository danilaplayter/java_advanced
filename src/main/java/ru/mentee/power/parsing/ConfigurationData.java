package ru.mentee.power.parsing;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ConfigurationData {

  private String serverUrl;
  private int port;
  private List<String> featureFlags;

}
