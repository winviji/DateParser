package com.assignment.DateParser.DateParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class DateTimeUtility {

  String defaultYear = "2001";
  String defaultHour = "00";
  String defaultMin = "00";
  String defaultSecond = "00";
  String defaultMonth = "Jan";
  String defaultday = "01";

  @SuppressWarnings("deprecation")
  public String getExpectedDateTime(String dateTimeString, String dateFormat, String separator)
      throws ParseException {
    SimpleDateFormat enteredFormat = new SimpleDateFormat(dateFormat);
    // Wed Aug 12 2009 00:00:00 GMT+0000

    // dd/ MM/ yyyy HH:mm Z

    enteredFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

    Date date = enteredFormat.parse(dateTimeString);

    DateFormat newFormat = new SimpleDateFormat("MMM dd yyyy hh:mm:ss z");
    newFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

    String expectedDate = newFormat.format(date);


    return expectedDate;



  }
}
