package ru.mentee.power.parsing;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonDataConverter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public String toJson(ConfigurationData data) {
    try {
      return objectMapper.writeValueAsString(data);
    } catch (Exception e) {
      throw new RuntimeException("Ошибка сериализации в Json", e);
    }
  }

  public ConfigurationData fromJson(String json) {
    try {
      return objectMapper.readValue(json, ConfigurationData.class);
    } catch (Exception e) {
      throw new RuntimeException("Ошибка десериализации из Json", e);
    }
  }

}
