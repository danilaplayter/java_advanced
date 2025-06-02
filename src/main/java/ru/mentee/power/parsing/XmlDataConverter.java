package ru.mentee.power.parsing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class XmlDataConverter {

  public String toXml(ConfigurationData data) {
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("configuration");
      doc.appendChild(rootElement);

      Element serverUrl = doc.createElement("serverUrl");
      serverUrl.appendChild(doc.createTextNode(data.getServerUrl()));
      rootElement.appendChild(serverUrl);

      Element port = doc.createElement("port");
      port.appendChild(doc.createTextNode(String.valueOf(data.getPort())));
      rootElement.appendChild(port);

      Element featureFlags = doc.createElement("featureFlags");
      for (String flag : data.getFeatureFlags()) {
        Element flagElement = doc.createElement("flag");
        flagElement.appendChild(doc.createTextNode(flag));
        featureFlags.appendChild(flagElement);
      }
      rootElement.appendChild(featureFlags);

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

      StringWriter writer = new StringWriter();
      transformer.transform(new DOMSource(doc), new StreamResult(writer));
      return writer.toString();

    } catch (ParserConfigurationException | TransformerException e) {
      throw new RuntimeException("Ошибка при преобразовании в XML", e);
    }
  }

  public ConfigurationData fromXml(String xml) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new InputSource(new StringReader(xml)));

      ConfigurationData data = new ConfigurationData();

      NodeList serverUrlList = doc.getElementsByTagName("serverUrl");
      if (serverUrlList.getLength() > 0) {
        data.setServerUrl(serverUrlList.item(0).getTextContent());
      }

      NodeList portList = doc.getElementsByTagName("port");
      if (portList.getLength() > 0) {
        data.setPort(Integer.parseInt(portList.item(0).getTextContent()));
      }

      List<String> flags = new ArrayList<>();

      NodeList featureFlagsList = doc.getElementsByTagName("featureFlags");
      if (featureFlagsList.getLength() > 0) {
        Node featureFlagsNode = featureFlagsList.item(0);
        NodeList flagList = featureFlagsNode.getChildNodes();

        for (int i = 0; i < flagList.getLength(); i++) {
          Node flagNode = flagList.item(i);
          if (flagNode.getNodeType() == Node.ELEMENT_NODE && "flag".equals(flagNode.getNodeName())) {
            flags.add(flagNode.getTextContent());
          }
        }
      }

      data.setFeatureFlags(flags);
      return data;

    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new RuntimeException("Ошибка при парсинге XML", e);
    }
  }
}