package com.assignment.DateParser.DateParser;

// Imports

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Class containing methods to initialize the browser and test the Date-time Strings.
 * 
 */

public class DateParserTest {
  protected WebDriver driver;

  // URL of the web application to be tested
  protected String testURL = "https://vast-dawn-73245.herokuapp.com/";

  protected DateTimeUtility dt = new DateTimeUtility();
  // Create a object of the readTestData class to be used to reading the test data
  protected readTestData readData = new readTestData();
  protected String browserName;
  protected String browserVersion;

  /*
   * Initialize the driver variable with instance of the browser and set the url
   */
  DateParserTest() {
    // Get the required browser name for testing from testData and initialize the driver value
    // accordingly

    browserName = readData.getTestBrowserName().get(0);
    browserVersion = readData.getTestBrowserName().get(1);

    if (browserName.equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().browserVersion(browserVersion).setup();


      driver = new ChromeDriver();



    } else if (browserName.equalsIgnoreCase("firefox")) {
      driver = new FirefoxDriver();
    }

  }


  @BeforeTest()
  protected void checks() {

    // Get the required browser name for testing from testData and initialize the driver value
    // accordingly before running each test method


    if (driver == null) {
      if (browserName.equalsIgnoreCase("chrome")) {
        WebDriverManager.chromedriver().browserVersion(browserVersion).setup();
        driver = new ChromeDriver();

      } else if (browserName.equalsIgnoreCase("firefox")) {
        driver = new FirefoxDriver();
      }
    }
    if (!driver.getCurrentUrl().contains(testURL)) {
      driver.get(testURL);
    }
  }



  @Test(priority = 0)
  public void TestUILabels() {
    Assert.assertTrue(dt.verifyLabelExists(driver,
        "Enter a text in any format, press submit to interpret it as a date."));

    Assert.assertTrue(dt.verifyLabelExists(driver, "Propine Date Parser"));

    Assert.assertTrue(dt.verifyLabelExists(driver, "Results"));
    Assert.assertTrue(dt.verifyPageTitle(driver, "Propine Date Parser"));


  }

  /*
   * Test by entering only the year value in 4 digits. Expected result is 1st of Jan of the entered
   * year since day and month are not entered. Default day = 01 and Default month = Jan
   */

  @Test(priority = 1)
  public void testYearFormat4Digit() {

    runTestByName("testYearFormat4Digit");



  }

  /*
   * Input a 3 digit value. Expected: Should be interpreted as Year Default day = 01 and Default
   * month = Jan
   */
  @Test(priority = 2)
  public void testYearFormat3Digit() {

    runTestByName("TestYearFormat3Digit");
  }

  /*
   * Input only a two digit number Expected: number between 01-12 should be interpreted as a month
   * Numbers greater than 12 should be interpreted as year
   */

  @Test(priority = 3)
  public void TestYearFormat2Digit() {

    runTestByName("Test2DigitNumber");
  }

  /*
   * Input two values indicating month and year in any order Month value range 01-12 and String
   * values like Jan, Feb, March etc including the entire month name. Invalid abbreviations for
   * month names should be ignored
   */
  @Test(priority = 4)
  public void TestMonthandYear() {

    runTestByName("TestMonthandYear");
  }

  /*
   * Input the month and date values , default year should be 2001 month range : 01-12 including
   * month names and abbreviations Date range : 1-30/31/28/29 Leap year tests should pass
   */

  @Test(priority = 5)
  public void TestMonthAndDate() {

    runTestByName("TestMonthAndDate");
  }


  /*
   * Input month date and year values Test for range , validity for each value as in above tests
   * Test for all possible formats
   */
  @Test(priority = 6)
  public void TestMonthDateYear() {

    runTestByName("TestMonthDateYear");
  }


  /*
   * Test the date time string inputs using a combination of separators Since requirements specify
   * that Date-time string can be entered in any format
   */
  @Test(priority = 7)
  public void TestAllowedSeparators() {
    runTestByName("SeparatorsTest");

  }

  /*
   * Input date-time string along with timezone conversion and AM/PM values
   */
  @Test(priority = 8)
  public void TestTimeZone() {
    runTestByName("TestTimeandTimeZone");
  }

  /*
   * Test the Labels, description text , page title in the UI
   */


  protected void runTestByName(String name) {



    SoftAssert sa = new SoftAssert();
    List<ArrayList<String>> testSet = readData.readTest(name);
    ArrayList<String> inputs = testSet.get(0);
    ArrayList<String> outputs = testSet.get(1);
    ArrayList<String> errors = new ArrayList<String>();

    if (testSet.size() > 2) {
      errors = testSet.get(2);
    }
    int itr = inputs.size();

    dt.waitForPageToLoad(driver);
    for (int i = 0; i < itr; i++) {
      dt.inputDate(driver, inputs.get(i));
      dt.clickSubmitButton(driver);

      String date = dt.readOutput(driver);


      sa.assertEquals(date, outputs.get(i),
          "\nInput date: " + inputs.get(i) + "\n Expected Output " + outputs.get(i)
              + "\n Actual Output " + date + "\n" + errors.get(i));
    }
    sa.assertAll();

  }

  @AfterTest
  protected void Quit() {

    driver.quit();
  }


}
