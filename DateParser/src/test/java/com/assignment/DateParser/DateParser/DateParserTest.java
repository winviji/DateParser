package com.assignment.DateParser.DateParser;


import io.github.bonigarcia.wdm.WebDriverManager;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class DateParserTest {
  protected WebDriver driver;

  protected String testURL = "https://vast-dawn-73245.herokuapp.com/";

  protected DateTimeUtility dt = new DateTimeUtility();
  protected readTestData readData = new readTestData();



  DateParserTest() {
    /*
     * driver = new FirefoxDriver(); driver.get(testURL);
     */


    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();



  }


  @BeforeTest()
  protected void checks() {
    /*
     * if (driver == null) { driver = new FirefoxDriver(); }
     */
    if (!driver.getCurrentUrl().contains(testURL)) {
      driver.get(testURL);
    }
  }

  @Test(priority = 0)
  public void openURL() throws ParseException {

    SoftAssert sa = new SoftAssert();

    String inputDate = "12-aug-2009";
    String expectedDate = "Wed Aug 12 2009 00:00:00 GMT+0000";

    waitForPageToLoad();
    inputDate(inputDate);

    clickSubmitButton();


    String date = readOutput();

    System.out.print("Expected String : " + expectedDate);

    sa.assertEquals(expectedDate, date);
    sa.assertAll();
  }


  @Test(priority = 1)
  public void testYearFormat4Digit() throws ParseException {

    runTestByName("testYearFormat4Digit");



  }

  @Test(priority = 2)
  public void testYearFormat3Digit() {

    runTestByName("TestYearFormat3Digit");
  }


  @Test(priority = 3)
  public void TestYearFormat2Digit() {

    runTestByName("Test2DigitNumber");
  }

  @Test(priority = 4)
  public void TestMonthandYear() {

    runTestByName("TestMonthandYear");
  }

  @Test(priority = 5)
  public void TestMonthAndDate() {

    runTestByName("TestMonthAndDate");
  }

  @Test(priority = 6)
  public void TestMonthDateYear() {

    runTestByName("TestMonthDateYear");
  }

  @Test(priority = 7)
  public void TestAllowedSeparators() {
    runTestByName("SeparatorsTest");

  }

  @Test(priority = 8)
  public void TestTimeZone() {
    runTestByName("TestTimeandTimeZone");
  }

  public void waitForPageToLoad() {
    new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated((By
        .name("date"))));

  }

  public void inputDate(String value) {

    new WebDriverWait(driver, 60)
        .until(ExpectedConditions.presenceOfElementLocated(By.name("date")));

    driver.findElement(By.name("date")).clear();
    driver.findElement(By.name("date")).sendKeys(value);

  }

  public void clickSubmitButton() {
    new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By
        .xpath("//input[@class='btn btn-default']")));

    driver.findElement(By.xpath("//input[@class='btn btn-default']")).click();

  }

  public String readOutput() {
    new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By
        .xpath("//div[@class = 'col-md-6']/div")));
    String result = driver.findElement(By.xpath("//div[@class = 'col-md-6']/div")).getText();

    return result;
  }

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

    waitForPageToLoad();
    for (int i = 0; i < itr; i++) {
      driver.findElement(By.name("date")).sendKeys(inputs.get(i));
      clickSubmitButton();

      String date = readOutput();


      sa.assertEquals(date, outputs.get(i), "Input date: " + inputs.get(i) + " " + errors.get(i));

    }
    sa.assertAll();

  }

  @AfterTest
  protected void Quit() {

    driver.quit();
  }


}
