# DateParser Tests

The Project in this repository is a maven project contain automated tests written in Selenium Webdriver using Java as the programming language, TestNG Framework. 


Folder Structure

The tests are placed under the below directory structure
src/test/java.com.assignment/DateParser/DateParser

The main test file is DateParserTest.java containing methods for each testcase.

The readTestData.java contains the methods used to read the input and expected output files 
from the provided XML file

The resources are placed under the below directory structure
src/test/resources

The testData.xml file contains the input, expected output and the error string for each test case.

The testData.xml file also allows user to specify the browser to be used for testing.
Current version of the code supports chrome and firefox browsers.
IMP: For chrome browser, the version number that matches the current version of chrome running on the local machine needs to be specified.
The default version value is set to 72.0.3626.69



The test code is capable of reading the input and output values from the xml file using the name attribute associated with each test.



The testng.xml file is used to specify the test class names used at runtime.

Running the tests at the local machine:

Local environment needs maven to run the code

All requied libraries will be downloaded by maven

TEST report:

The test report is available in //DateParser/surefire-reports