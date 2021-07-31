package com.assignment.DateParser.DateParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/*
 * DateTimeUtility class Contains utility methods that help in browser interaction
 */
public class DateTimeUtility1 {

  String defaultYear = "2001";
  String defaultHour = "00";
  String defaultMin = "00";
  String defaultSecond = "00";
  String defaultMonth = "Jan";
  String defaultday = "01";

  /*
   * Method to be used to generate expected output from the input. Currently not used for testing.
   * 
   * @param dateTimeString: The input date time string
   * 
   * @param dateFormat: Format of the input string example: MM:DD:YYYY
   * 
   * @param separator: String used as separator
   * 
   * @return : Expected output string
   */

  @SuppressWarnings("deprecation")
  protected String getExpectedDateTime(String dateTimeString, String dateFormat, String separator)
      throws ParseException {
    SimpleDateFormat enteredFormat = new SimpleDateFormat(dateFormat);


    enteredFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

    Date date = enteredFormat.parse(dateTimeString);

    DateFormat newFormat = new SimpleDateFormat("MMM dd yyyy hh:mm:ss z");
    newFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

    String expectedDate = newFormat.format(date);


    return expectedDate;



  }

  /*
   * Wait for the page to load , timeout in 60 seconds
   */
  protected void waitForPageToLoad(WebDriver driver) {



    new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated((By
        .name("date"))));

  }

  /*
   * Input the specified string into the text box
   */

  protected void inputDate(WebDriver driver, String value) {

    new WebDriverWait(driver, 60)
        .until(ExpectedConditions.presenceOfElementLocated(By.name("date")));

    driver.findElement(By.name("date")).clear();
    driver.findElement(By.name("date")).sendKeys(value);

  }

  /*
   * Click the submit button
   */
  protected void clickSubmitButton(WebDriver driver) {
    new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By
        .xpath("//input[@class='btn btn-default']")));

    driver.findElement(By.xpath("//input[@class='btn btn-default']")).click();

  }

  /*
   * Read the current result displayed in the application
   */
  protected String readOutput(WebDriver driver) {
    new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By
        .xpath("//div[@class = 'col-md-6']/div")));
    String result = driver.findElement(By.xpath("//div[@class = 'col-md-6']/div")).getText();

    return result;
  }

  protected boolean verifyLabelExists(WebDriver driver, String label) {
    waitForPageToLoad(driver);
    if (driver.getPageSource().contains(label))
      return true;
    else
      return false;
  }

  protected boolean verifyPageTitle(WebDriver driver, String title) {

    waitForPageToLoad(driver);
    if (driver.getTitle().equalsIgnoreCase(title))
      return true;
    else
      return false;
  }
}
