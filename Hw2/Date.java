/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */
  private int year;
  private int month;
  private int day;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(final int month, final int day, final int year) {
    if(!isValidDate(month, day, year)){
      System.out.println("not a valid date");
      System.exit(0);
    }
    else{
      this.month = month; //不加会变成0
      this.day = day;
      this.year = year;
    }

  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(final String s) {
    final String[] theDate = s.split("/");
    if (!theDate[0].matches("[0-9]+") || !theDate[1].matches("[0-9]+") || !theDate[2].matches("[0-9]+")){    //"[0-9]+"表示匹配0-9一次或者多次。 这行保证了是数字
      System.out.println("Wrong date format");
      System.exit(0);
    }  
    
    final int month = Integer.parseInt(theDate[0]); //Integ.parseInt用来把string转换成Int
    final int day = Integer.parseInt(theDate[1]);
    final int year = Integer.parseInt(theDate[2]);

    if(!isValidDate(month, day, year)){
      System.out.println("not a valid date");
      System.exit(0);
    }
    else{
      this.month = month;
      this.day = day;
      this.year = year;
    }
 
}

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(final int year) {
    //return true;                        // replace this line with your solution
  if((year%4==0 && year%100 != 0)||(year % 400 == 0)){
    return true;
  }
  else{
    return false;
  }
}

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(final int month, final int year) {
    // return 0;                           // replace this line with your solution
    switch(month){
      case 2: if(isLeapYear(year)) return 29;
                else return 28;
      case 4:
      case 6:
      case 9:
      case 11: 
        return 30;
      default:
        return 31;
    }
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(final int month, final int day, final int year) {
    // return true;                        // replace this line with your solution
    if (month<1 || month >12){
      System.out.println("Wrong month input!");
      return false;
    }

    if (year<1){
      System.out.println("Wrong year input!");
      return false;
    }

    if (day<1 || day>daysInMonth(month, year)){
      System.out.println("Wrong date input!");
      return false;
    }
    else{
      return true;
    }
}

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    // return "stuff";                     // replace this line with your solution
    return Integer.toString(this.month) + "/" + Integer.toString(this.day) + "/" + Integer.toString(this.year);

  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(final Date d) {
    // return true;                        // replace this line with your solution
    if (this.difference(d)< 0){
      return true;
    } 
      return false;
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(final Date d) {
    // return true;                        // replace this line with your solution
    if(this.difference(d)>0){
      return true;
    }
      return false;
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
    // return 0;                           // replace this line with your solution
    int days = this.day;
    for(int i = 1; i <this.month; i++){
    days += daysInMonth(i, this.year);
    }
    return days;
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
    //  return 0;                         // replace this line with your solution
    // 算法： 同年份直接按一年内第几天相减，后面分y1>y2和y1>y2
    int s1=this.dayInYear(), s2=d.dayInYear(), y1=this.year, y2=d.year;
      if(y1 == y2){
        return s1-s2;
      }
      else if(y1 < y2){
        for (int i = y1; i <y2; i++){
          if(isLeapYear(i)){
            s1 -= 366;
          }
          else{
            s1 -= 365;
          }
        }
        return s1-s2;
      }
      else{
        for (int i=y2; i<y1; i++){
          if(isLeapYear(i)){
            s1 += 366;
          }
          else{
            s1 += 365;
          }
        }
      return s1-s2;
        }
      
      }
        


    
  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    final Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    final Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    final Date d4 = new Date("2/27/1977");
    final Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}

// 写for loop记得declare int i