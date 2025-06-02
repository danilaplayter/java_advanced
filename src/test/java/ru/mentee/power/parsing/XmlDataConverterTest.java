package ru.mentee.power.parsing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class XmlDataConverterTest {
  private final XmlDataConverter converter = new XmlDataConverter();

  @Test
  @DisplayName("Сериализация ConfigurationData в XML")
  void shouldSerializeConfigurationDataToXml() {
    ConfigurationData data = new ConfigurationData(
        "http://xml.test.com",
        8888,
        List.of("XML_F1", "XML_F2")
    );

    String xml = converter.toXml(data);

    // Проверки
    assertThat(xml)
        .contains("<serverUrl>http://xml.test.com</serverUrl>")
        .contains("<port>8888</port>")
        .contains("<flag>XML_F1</flag>")
        .contains("<flag>XML_F2</flag>")
        .contains("<featureFlags>");
  }

  @Test
  @DisplayName("Десериализация XML в ConfigurationData (полный набор данных)")
  void shouldDeserializeCompleteXmlToConfigurationData() {
    // Подготовка тестовых данных
    String xml = """
            <configuration>
                <serverUrl>http://from.xml</serverUrl>
                <port>5678</port>
                <featureFlags>
                    <flag>XFLAG</flag>
                    <flag>YFLAG</flag>
                </featureFlags>
            </configuration>
            """;

    ConfigurationData expectedData = new ConfigurationData(
        "http://from.xml",
        5678,
        List.of("XFLAG", "YFLAG")
    );

   ConfigurationData actualData = converter.fromXml(xml);


    assertThat(actualData)
        .usingRecursiveComparison()
        .isEqualTo(expectedData);
  }

  @Test
  @DisplayName("Десериализация XML с минимальными данными")
  void shouldDeserializeMinimalXmlToConfigurationData() {
    String xml = """
            <configuration>
                <serverUrl>http://minimal.xml</serverUrl>
                <port>1234</port>
            </configuration>
            """;

    ConfigurationData expectedData = new ConfigurationData(
        "http://minimal.xml",
        1234,
        List.of() // Пустой список флагов
    );

    ConfigurationData actualData = converter.fromXml(xml);

    assertThat(actualData.getServerUrl()).isEqualTo("http://minimal.xml");
    assertThat(actualData.getPort()).isEqualTo(1234);
    assertThat(actualData.getFeatureFlags()).isEmpty();
  }

  @Test
  @DisplayName("Десериализация XML с отсутствующими featureFlags")
  void shouldDeserializeXmlWithoutFeatureFlags() {
    String xml = """
            <configuration>
                <serverUrl>http://noflags.xml</serverUrl>
                <port>4321</port>
                <!-- featureFlags отсутствует -->
            </configuration>
            """;

    ConfigurationData actualData = converter.fromXml(xml);

    assertThat(actualData.getServerUrl()).isEqualTo("http://noflags.xml");
    assertThat(actualData.getPort()).isEqualTo(4321);
    assertThat(actualData.getFeatureFlags()).isEmpty();
  }
}