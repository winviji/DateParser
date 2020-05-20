package com.assignment.DateParser.DateParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class readTestData {
  protected File file;

  protected DocumentBuilder documentBuilder;

  protected Document testXml;

  public readTestData() {
    file = new File("src/test/resources/testData.xml");

    try {
      documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      testXml = documentBuilder.parse(file);
    } catch (SAXException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    testXml.getDocumentElement().normalize();


  }

  public List<ArrayList<String>> readTest(String name) {

    // NodeList test = testXml.getElementsByTagName("Test");
    List<ArrayList<String>> testSet = new ArrayList<ArrayList<String>>();
    ArrayList<String> inputValues = new ArrayList<String>();;
    ArrayList<String> outputValues = new ArrayList<String>();;
    ArrayList<String> errorValues = new ArrayList<String>();;

    NodeList test = testXml.getElementsByTagName("Test");
    boolean flag = false;
    for (int itr = 0; itr < test.getLength() && flag == false; itr++) {

      Node node = test.item(itr);

      // System.out.println("\nNode Name :" + node.getNodeName());
      if (node.getAttributes().getNamedItem("name") != null) {
        if (node.getAttributes().getNamedItem("name").getNodeValue().equals(name)) {
          flag = true;
          Element input = (Element) node;
          Element output = (Element) node;
          NodeList inputNodes = input.getElementsByTagName("input");
          for (int i = 0; i < inputNodes.getLength(); i++) {
            inputValues.add(inputNodes.item(i).getTextContent());
          }

          NodeList outputNodes = output.getElementsByTagName("output");
          for (int i = 0; i < outputNodes.getLength(); i++) {
            outputValues.add(outputNodes.item(i).getTextContent());
          }
          if (output.getElementsByTagName("error").getLength() != 0) {
            NodeList errorNodes = output.getElementsByTagName("error");
            for (int i = 0; i < errorNodes.getLength(); i++) {
              errorValues.add(errorNodes.item(i).getTextContent());
            }
          }
          /*
           * NodeList outputValues = output.getElementsByTagName("output");
           * 
           * 
           * 
           * System.out.println("input: "+
           * eElement.getElementsByTagName("input").item(itr).getTextContent());
           * 
           * System.out.println("output: "+
           * eElement.getElementsByTagName("output").item(itr).getTextContent());
           */
        }

      }
    }
    testSet.add(inputValues);
    testSet.add(outputValues);
    testSet.add(errorValues);

    return testSet;


  }
}
