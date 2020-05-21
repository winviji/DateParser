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

  // Read browser configuration data
  protected ArrayList<String> getTestBrowserName() {
    String browserName = "";
    String browserVersion = "";

    ArrayList<String> browserDetails = new ArrayList<String>();
    NodeList browserList = testXml.getElementsByTagName("Browser");

    Node node = browserList.item(0);
    Element element = (Element) node;

    browserName = element.getElementsByTagName("value").item(0).getTextContent().toLowerCase();
    browserVersion = element.getElementsByTagName("version").item(0).getTextContent().toLowerCase();
    if (browserName.isEmpty())
      browserName = "Chrome";

    if (browserVersion.isEmpty())
      browserVersion = "72.0.3626.69";

    browserDetails.add(browserName);
    browserDetails.add(browserVersion);

    return browserDetails;

  }

  /*
   * Method to read test data from the given xml file Return the test data as List
   */
  public List<ArrayList<String>> readTest(String name) {

    // NodeList test = testXml.getElementsByTagName("Test");
    List<ArrayList<String>> testSet = new ArrayList<ArrayList<String>>();
    ArrayList<String> inputValues = new ArrayList<String>();;
    ArrayList<String> outputValues = new ArrayList<String>();;
    ArrayList<String> errorValues = new ArrayList<String>();;

    // Read the elements with tag name :Test
    NodeList test = testXml.getElementsByTagName("Test");
    boolean flag = false;
    // Iterate through all the test nodelist till the test with given name is found
    for (int itr = 0; itr < test.getLength() && flag == false; itr++) {

      Node node = test.item(itr);

      // Find the test elements that match the given test name
      // Read the input, output and error values from the matching element
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
