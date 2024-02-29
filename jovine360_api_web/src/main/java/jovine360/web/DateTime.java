package jovine360.web;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.TimeZone;

/**
 *
 * @author JOSSY-PC
 * This API is designed to help with Date-Time manipulation, ranging from current date, time
 * calculating expiry dates, and extracting day, month, year from a date String value.
 */
public class DateTime {


    /**
     * This method is used to get the Current time. It is a static method and 
     * can be accessed via the class.
     * <p>
     * Kindly note that this method returns the System time where it program resides e.g server time. 
     * In a web application instance, user's local current time can be gotten via a scripting code 
     * from the browser.
     * </p>
     * @return a string value of the system current time. 
     * <pre>
     *      String time = DateTime.getCurrentTime();
     * </pre>
     */
     public static String getCurrentTime(){
        String datetime = "";
        Date date = Calendar.getInstance(TimeZone.getDefault()).getTime();
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDate();

        Date dat = new java.sql.Date(year, month, day);

        int hr = date.getHours();
        int min = date.getMinutes();
        int sec = date.getSeconds();

//        String dDate = String.valueOf(dat);
        String tTime = String.valueOf(hr +":"+min+":"+sec);

        datetime = tTime;

        return datetime;
     }
     /**
     * This method is used to get the Current Datetime. It is a static method and 
     * can be accessed via the class.
     * <p>
     * Kindly note that this method returns the System time where it program resides e.g server time. 
     * In a web application instance, user's local current datetime can be gotten via a scripting code 
     * from the browser.
     * </p>
     * @return a string value of the system current time. 
     * <pre>
     *      String time = DateTime.getCurrentDateTime();
     * </pre>
     */
     public static String getCurrentDateTime(){
        String datetime = "";
        Date date = Calendar.getInstance(TimeZone.getDefault()).getTime();
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDate();

        Date dat = new java.sql.Date(year, month, day);

        int hr = date.getHours();
        int min = date.getMinutes();
        int sec = date.getSeconds();

        String dDate = String.valueOf(dat);
        String tTime = String.valueOf(hr +":"+min+":"+sec);

        datetime = dDate + " " + tTime;

        return datetime;
    }
    /**
     * This method is used to get the Current date. It is a static method and 
     * can be accessed via the class.
     * <p>
     * Kindly note that this method returns the System current date.
     * </p>
     * @return a string value of the system current time. 
     * <pre>
     *      String time = DateTime.getCurrentTime();
     * </pre>
     */ 
    public static String getCurrentDate(){
         Date dates = new Date();
         int year = dates.getYear();
         int month = dates.getMonth();
         int day = dates.getDate();

         Date dat = new java.sql.Date(year, month, day);
         String dDate = String.valueOf(dat);

         return dDate;

     }
    /**
     * This method is used to get the date from a date argument.It is a static method and 
 can be accessed via the class.
     * @param date represets the date argument passed for extracting the date of the month
     * @return an int value of the date from the date argument passed. 
     * <pre>
     *      int date = DateTime.getDate(dateObject);
     * </pre>
     */ 
     public static int getDate(String date){
         int dat = 0, year = 0, month = 0, day = 0;

         StringTokenizer token = new StringTokenizer(date, "-");     
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             year = Integer.parseInt(token.nextToken());
             month = Integer.parseInt(token.nextToken());
             dat = Integer.parseInt(token.nextToken());
         }

         return dat;
     }
     /**
     * This method is used to get the month from a date argument.It is a static method and 
 can be accessed via the class.   
     * @param date represets the date argument passed for extracting the month of the year
     * @return an int value of the month from the date argument passed. 
     * <pre>
     *      int mon = DateTime.getMonth(dateObject);
     * </pre>
     */ 
     public static int getMonth(String date){
         int mon = 0,year = 0, month = 0, day = 0;
         StringTokenizer token = new StringTokenizer(date, "-");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             year = Integer.parseInt(token.nextToken());
             mon = Integer.parseInt(token.nextToken());
             day = Integer.parseInt(token.nextToken());
         }

         return mon;
     }
     /**
     * This method is used to get the year from a date argument.It is a static method and 
 can be accessed via the class.   
     * @param date represets the date argument passed for extracting the year value
     * @return an int value of the year from the date argument passed. 
     * <pre>
     *      int year = DateTime.getYear(dateObject);
     * </pre>
     */ 
     public static int getYear(String date){
         int yr = 0, year = 0, month = 0, day = 0;
         StringTokenizer token = new StringTokenizer(date, "-");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             yr = Integer.parseInt(token.nextToken());
             month = Integer.parseInt(token.nextToken());
             day = Integer.parseInt(token.nextToken());
         }

         return yr;
     }
     /**
     * This method is used to get the year from a datetime argument.It is a static method and 
 can be accessed via the class.   
     * @param datetime represets the datetime argument passed for extracting the date of the month
     * @return an int value of the year from the date argument passed. 
     * <pre>
     *      int year = DateTime.getYear(dateTimeObject);
     * </pre>
     */ 
     public static int getYears(String datetime){
         int hr = 0, min = 0, sec = 0;
         int yr = 0, year = 0, month = 0, day = 0;
         StringTokenizer token = new StringTokenizer(datetime, " ");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String date = token.nextToken();
             String time = token.nextToken();
             
             StringTokenizer tok = new StringTokenizer(date, "-");
            if(tok.countTokens() == 0){
                throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
            }else{
                yr = Integer.parseInt(tok.nextToken());
                month = Integer.parseInt(tok.nextToken());
                day = Integer.parseInt(tok.nextToken());
            }
         }

         return yr;
     }
     /**
     * This method is used to get the date from a date argument.It is a static method and 
 can be accessed via the class.
     * @param datetime represets the datetime argument passed for extracting the date of the month
     * @return an int value of the date from the datetime argument passed. 
     * <pre>
     *      int date = DateTime.getDays(dateTimeObject);
     * </pre>
     */ 
     public static int getDays(String datetime){
         int hr = 0, min = 0, sec = 0;
         int yr = 0, year = 0, month = 0, day = 0;
         StringTokenizer token = new StringTokenizer(datetime, " ");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String date = token.nextToken();
             String time = token.nextToken();
             
             StringTokenizer tok = new StringTokenizer(date, "-");
            if(tok.countTokens() == 0){
                throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
            }else{
                yr = Integer.parseInt(tok.nextToken());
                month = Integer.parseInt(tok.nextToken());
                day = Integer.parseInt(tok.nextToken());
            }
         }

         return day;
     }
     /**
     * This method is used to get the month from a date argument.It is a static method and 
 can be accessed via the class.   
     * @param datetime represets the datetime argument passed for extracting the date of the month
     * @return an int value of the month from the datetime argument passed. 
     * <pre>
     *      int mon = DateTime.getMonths(dateTimeObject);
     * </pre>
     */ 
     public static int getMonths(String datetime){
         int hr = 0, min = 0, sec = 0;
         int yr = 0, year = 0, month = 0, day = 0;
         StringTokenizer token = new StringTokenizer(datetime, " ");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String date = token.nextToken();
             String time = token.nextToken();
             
             StringTokenizer tok = new StringTokenizer(date, "-");
            if(tok.countTokens() == 0){
                throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
            }else{
                yr = Integer.parseInt(tok.nextToken());
                month = Integer.parseInt(tok.nextToken());
                day = Integer.parseInt(tok.nextToken());
            }
         }

         return month;
     }
     /**
     * This method is used to get the hour from a time argument.It is a static method and 
 can be accessed via the class.   
     * @param time represets the time argument passed for extracting the hour value
     * @return an int value of the hour from the time argument passed. 
     * <pre>
     *      int mon = DateTime.getHourOnly(Time);
     * </pre>
     */ 
     public static int getHourOnly(String time){
         int hr = 0, min = 0, sec = 0;
         StringTokenizer token = new StringTokenizer(time, ":");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             hr = Integer.parseInt(token.nextToken());
             min = Integer.parseInt(token.nextToken());
             sec = Integer.parseInt(token.nextToken());
         }

         return hr;
     }
     /**
     * This method is used to get the minutes from a time argument.It is a static method and 
 can be accessed via the class.   
     * @param time represets the time argument passed for extracting the minutes value
     * @return an int value of the minutes from the time argument passed. 
     * <pre>
     *      int mon = DateTime.getMinutesOnly(Time);
     * </pre>
     */ 
     public static int getMinutesOnly(String time){
         int hr = 0, min = 0, sec = 0;
         StringTokenizer token = new StringTokenizer(time, ":");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             hr = Integer.parseInt(token.nextToken());
             min = Integer.parseInt(token.nextToken());
             sec = Integer.parseInt(token.nextToken());
         }

         return min;
     }
     /**
     * This method is used to get the seconds from a time argument.It is a static method and 
 can be accessed via the class.   
     * @param time represets the time argument passed for extracting the seconds value
     * @return an int value of the seconds from the time argument passed. 
     * <pre>
     *      int mon = DateTime.getSecondsOnly(Time);
     * </pre>
     */ 
     public static int getSecondsOnly(String time){
         int hr = 0, min = 0, sec = 0;
         StringTokenizer token = new StringTokenizer(time, ":");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             hr = Integer.parseInt(token.nextToken());
             min = Integer.parseInt(token.nextToken());
             sec = Integer.parseInt(token.nextToken());
         }

         return sec;
     }
     /**
     * This method is used to get the hour from a time argument.It is a static method and 
 can be accessed via the class.   
     * @param datetime represets the time argument passed for extracting the hour value
     * @return an int value of the hour from the datetime argument passed. 
     * <pre>
     *      int mon = DateTime.getHour(datetime);
     * </pre>
     */ 
     public static int getHour(String datetime){
         int hr = 0, min = 0, sec = 0;
         StringTokenizer token = new StringTokenizer(datetime, " ");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String date = token.nextToken();
             String time = token.nextToken();
             
             StringTokenizer tok = new StringTokenizer(time, ":");
             if(tok.countTokens() == 0){
                 throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
             }else{
                hr = Integer.parseInt(tok.nextToken());
                min = Integer.parseInt(tok.nextToken());
                sec = Integer.parseInt(tok.nextToken());
             }             
         }

         return hr;
     }
     /**
     * This method is used to get the minutes from a time argument.It is a static method and 
 can be accessed via the class.   
     * @param datetime represets the time argument passed for extracting the minutes value
     * @return an int value of the minutes from the time argument passed. 
     * <pre>
     *      int mon = DateTime.getMinutes(datetime);
     * </pre>
     */ 
     public static int getMinutes(String datetime){
         int hr = 0, min = 0, sec = 0;
         StringTokenizer token = new StringTokenizer(datetime, " ");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String date = token.nextToken();
             String time = token.nextToken();
             
             StringTokenizer tok = new StringTokenizer(time, ":");
             if(tok.countTokens() == 0){
                 throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
             }else{
                hr = Integer.parseInt(tok.nextToken());
                min = Integer.parseInt(tok.nextToken());
                sec = Integer.parseInt(tok.nextToken());
             }             
         }

         return min;
     }
     /**
     * This method is used to get the seconds from a time argument.It is a static method and 
 can be accessed via the class.   
     * @param datetime represets the time argument passed for extracting the seconds value
     * @return an int value of the seconds from the time argument passed. 
     * <pre>
     *      int mon = DateTime.getSeconds(datetime);
     * </pre>
     */ 
     public static int getSeconds(String datetime){
         int hr = 0, min = 0, sec = 0;
         StringTokenizer token = new StringTokenizer(datetime, " ");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String date = token.nextToken();
             String time = token.nextToken();
             
             StringTokenizer tok = new StringTokenizer(time, ":");
             if(tok.countTokens() == 0){
                 throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
             }else{
                hr = Integer.parseInt(tok.nextToken());
                min = Integer.parseInt(tok.nextToken());
                sec = Integer.parseInt(tok.nextToken());
             }             
         }

         return sec;
     }

     /**
      * <p>
      * This method is used to generate expiry dates based the on the date object
      * passed as parameter. it works with the getCurrentDate() method in the
      * same package, which can be invoked/passed directly to this method or
      * stored in a String variable object and in turn passed to this method:
      *</p>
      * <pre>
      *     E.g
      *         String date = DateTime.getCurrentDate(); from DATE package.
      *         String expireDate = DateTime.getExpireDate(date, "year|month|day", validPeriod);
      * </pre>
      * 
      * OR
      * 
      * <pre>
      *         String expireDate = DateTime.getExpireDate(DateTime.getCurrentDate(), "year|month|day", validPeriod);
      * </pre>
      *
      * 
      * @param date represents the String object containing the date value representing the start date.
      * @param dateFormat is a  flag representing the validity format whether its a year, month or day.
      * it can only be any of this option with no blank space before or after, ie "year" or "month" or "day".
      * @param value represents the valid period in numbers depending on the dateformat specified.
      * @return a String object of the newly generated date representing the expiry date.
      * 
      */
     public static String getExpireDate(String date, String dateFormat, int value){
         String dateVal = "";
         int  year = 0, month = 0, day = 0;
         StringTokenizer token = new StringTokenizer(date, "-");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
              year = Integer.parseInt(token.nextToken());
              month = Integer.parseInt(token.nextToken());
              day = Integer.parseInt(token.nextToken());
         }
         if(dateFormat.trim().equalsIgnoreCase("year")){
             int newYr = year + value;
             String years = String.valueOf(newYr);
             String mons = String.valueOf(month);
             String days = String.valueOf(day);
             dateVal = years + "-" + mons + "-" + days;
         }else if(dateFormat.trim().equalsIgnoreCase("month")){
             int newMon = month + value;
             if(newMon > 12){
                 newMon = newMon - 12;
                 year = year + 1;

                 String years = String.valueOf(year);
                 String mons = String.valueOf(newMon);
                 if(month == 4 || month == 6 || month == 9 || month == 11){
                     if(day > 30){
                         day = 30;
                     }
                 }else if(month == 2){
                     if(day > 28){
                         day = 28;
                     }
                 }
                 String days = String.valueOf(day);
                 dateVal = years + "-" + mons + "-" + days;
             }else{
                 String years = String.valueOf(year);
                 String mons = String.valueOf(newMon);

                 if(month == 4 || month == 6 || month == 9 || month == 11){
                     if(day > 30){
                         day = 30;
                     }
                 }else if(month == 2){
                     if(day > 28){
                         day = 28;
                     }
                 }
                 String days = String.valueOf(day);
                 dateVal = years + "-" + mons + "-" + days;
             }
             
         }else if(dateFormat.trim().equalsIgnoreCase("day")){
             int newDy = day + value;
             if(month == 4 || month == 6 || month == 9 || month == 11){
                 if(newDy > 30){
                     newDy = newDy - 30;
                     month = month + 1;
                 }
             }else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                 if(newDy > 31){
                     newDy = newDy - 31;
                     month = month + 1;
                     if(month > 12){
                         month = 1;
                         year++;
                     }else if(month < 12){
                         month = month + 1;
                     }
                 }
             }else if(month == 2){
                 if(newDy > 28){
                     newDy = newDy - 28;
                     month = month + 1;
                 }
             }
             String years = String.valueOf(year);
             String mons = String.valueOf(month);
             String days = String.valueOf(newDy);
             dateVal = years + "-" + mons + "-" + days;
         }

         return dateVal;
     }
     /**
      * This method is used for formatting the date value passed as a String parameter
      * in the format passed with date suffix appended.
      * @param date represents the date String object passed
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted date value
      * 
      *     <pre>
      *         Example:
      *         2016 January, 12th; // for 'yyyymmdd' format
      *     </pre>
      * 
      */
     public static String getFormatLongDateSuffix(String date, String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         StringTokenizer token = new StringTokenizer(date, "-");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String flag = "";
             year = Integer.parseInt(token.nextToken());
             month = Integer.parseInt(token.nextToken());
             day = Integer.parseInt(token.nextToken());
             
             
//             String[] mon_index = {"1","2","3","4","5","6","7","8","9","10","11","12"};
             String[] mons = {"January","February","March","April","May","June","July","August","September","October","November","December"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};

             for(int i = 0; i < days.length; i++){
                 if(day == days[i]){
//                     System.out.println("Suffix: "+ suffix[i]);
                     suf = suffix[i];
                     break;
                 }
             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day+suf);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+suf+ " "+ mons[month-1]+", "+ year);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+suf+", "+ year);
             }
         }

      return status;
     }
     /**
      * This method is used for formatting the date value passed as a String parameter
      * in the format passed without date suffix appended.
      * @param date represents the date String object passed
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted date value
      * 
      *     <pre>
      *         Example:
      *         2016 January, 12; // for 'yyyymmdd' format
      *     </pre>
      * 
      */
     public static String getFormatLongDate(String date, String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         StringTokenizer token = new StringTokenizer(date, "-");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String flag = "";
             year = Integer.parseInt(token.nextToken());
             month = Integer.parseInt(token.nextToken());
             day = Integer.parseInt(token.nextToken());


//             String[] mon_index = {"1","2","3","4","5","6","7","8","9","10","11","12"};
             String[] mons = {"January","February","March","April","May","June","July","August","September","October","November","December"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
//             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
//                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};
//
//             for(int i = 0; i < days.length; i++){
//                 if(day == days[i]){
////                     System.out.println("Suffix: "+ suffix[i]);
//                     suf = suffix[i];
//                     break;
//                 }
//             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+ " "+ mons[month-1]+", "+ year);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+", "+ year);
             }
         }

      return status;
     }
     /**
      * This method is used for generating a formatted current date value 
      * in the format passed as an argument,  with date suffix appended.
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted date value
      * <pre>
      *         Example:
      *         2016 January, 12th; // for 'yyyymmdd' format
      *     </pre>
      */
     public static String getCurrentFormatLongDateSuffix(String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         String date = DateTime.getCurrentDate();
         StringTokenizer token = new StringTokenizer(date, "-");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String flag = "";
             year = Integer.parseInt(token.nextToken());
             month = Integer.parseInt(token.nextToken());
             day = Integer.parseInt(token.nextToken());

//             String[] mon_index = {"1","2","3","4","5","6","7","8","9","10","11","12"};
             String[] mons = {"January","February","March","April","May","June","July","August","September","October","November","December"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};

             for(int i = 0; i < days.length; i++){
                 if(day == days[i]){
//                     System.out.println("Suffix: "+ suffix[i]);
                     suf = suffix[i];
                     break;
                 }
             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day+suf);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+suf+ " "+ mons[month-1]+", "+ year);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+suf+", "+ year);
             }
         }

      return status;
     }
     /**
      * This method is used for generating a formatted current date value 
      * in the format passed as an argument,  without date suffix appended.
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted date value
      * 
      *     <pre>
      *         Example:
      *         2016 January, 12th; // for 'yyyymmdd' format
      *     </pre>
      * 
      */
     public static String getCurrentFormatLongDate(String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         String date = DateTime.getCurrentDate();
         StringTokenizer token = new StringTokenizer(date, "-");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String flag = "";
             year = Integer.parseInt(token.nextToken());
             month = Integer.parseInt(token.nextToken());
             day = Integer.parseInt(token.nextToken());

//             String[] mon_index = {"1","2","3","4","5","6","7","8","9","10","11","12"};
             String[] mons = {"January","February","March","April","May","June","July","August","September","October","November","December"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
//             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
//                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};
//
//             for(int i = 0; i < days.length; i++){
//                 if(day == days[i]){
////                     System.out.println("Suffix: "+ suffix[i]);
//                     suf = suffix[i];
//                     break;
//                 }
//             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+ " "+ mons[month-1]+", "+ year);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+", "+ year);
             }
         }

      return status;
     }
     /**
      * This method is used for formatting the date value passed as a String parameter
      * in the format passed with date suffix appended.
      * @param date represents the date String object passed
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted date value with month abbreviated.
      *
      *     <pre>
      *         Example:
      *         2016 January, 12; // for 'yyyymmdd' format
      *     </pre>
      * 
      */
     public static String getShortDateFormatSuffix(String date, String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         StringTokenizer token = new StringTokenizer(date, "-");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             year = Integer.parseInt(token.nextToken());
             month = Integer.parseInt(token.nextToken());
             day = Integer.parseInt(token.nextToken());
             
//             String[] mon_index = {"01","02","03","04","05","06","07","08","09","10","11","12"};
             String[] mons = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};

             for(int i = 0; i < days.length; i++){
                 if(day == days[i]){
//                     System.out.println("Suffix: "+ suffix[i]);
                     suf = suffix[i];
                     break;
                 }
             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day+suf);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+suf+ " "+ mons[month-1]+", "+ year);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+suf+", "+ year);
             }
         }

         return status;
     }
     /**
      * This method is used for formatting the date value passed as a String parameter
      * in the format passed without date suffix appended.
      * @param date represents the date String object passed
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted date value with month abbreviated.
      * 
      *     <pre>
      *         Example:
      *         2016 January, 12; // for 'yyyymmdd' format
      *     </pre>
      *
      */
     public static String getShortDateFormat(String date, String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         StringTokenizer token = new StringTokenizer(date, "-");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             year = Integer.parseInt(token.nextToken());
             month = Integer.parseInt(token.nextToken());
             day = Integer.parseInt(token.nextToken());

//             String[] mon_index = {"01","02","03","04","05","06","07","08","09","10","11","12"};
             String[] mons = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
//             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
//                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};
//
//             for(int i = 0; i < days.length; i++){
//                 if(day == days[i]){
////                     System.out.println("Suffix: "+ suffix[i]);
//                     suf = suffix[i];
//                     break;
//                 }
//             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+ " "+ mons[month-1]+", "+ year);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+", "+ year);
             }
         }

         return status;
     }
     /**
      * This method is used for generating a formatted current date value 
      * in the format passed as an argument, with date suffix appended.
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted date value with an abbreviated month spelt
      * 
      *     <pre>
      *         Example:
      *         2016 January, 12th; // for 'yyyymmdd' format
      *     </pre>
      * 
      */
     public static String getCurrentShortDateFormatSuffix(String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         String date = DateTime.getCurrentDate();
         StringTokenizer token = new StringTokenizer(date, "-");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             year = Integer.parseInt(token.nextToken());
             month = Integer.parseInt(token.nextToken());
             day = Integer.parseInt(token.nextToken());

//             String[] mon_index = {"01","02","03","04","05","06","07","08","09","10","11","12"};
             String[] mons = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};

             for(int i = 0; i < days.length; i++){
                 if(day == days[i]){
//                     System.out.println("Suffix: "+ suffix[i]);
                     suf = suffix[i];
                     break;
                 }
             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day+suf);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+suf+ " "+ mons[month-1]+", "+ year);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+suf+", "+ year);
             }
         }

         return status;
     }
     /**
      * This method is used for generating a formatted current date value 
      * in the format passed as an argument, without date suffix appended.
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted date value with an abbreviated month spelt
      * 
      *     <pre>
      *         Example:
      *         2016 January, 12th; // for 'yyyymmdd' format
      *     </pre>
      * 
      */
     public static String getCurrentShortDateFormat(String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         String date = DateTime.getCurrentDate();
         StringTokenizer token = new StringTokenizer(date, "-");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             year = Integer.parseInt(token.nextToken());
             month = Integer.parseInt(token.nextToken());
             day = Integer.parseInt(token.nextToken());

//             String[] mon_index = {"01","02","03","04","05","06","07","08","09","10","11","12"};
             String[] mons = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
//             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
//                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};
//
//             for(int i = 0; i < days.length; i++){
//                 if(day == days[i]){
////                     System.out.println("Suffix: "+ suffix[i]);
//                     suf = suffix[i];
//                     break;
//                 }
//             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+ " "+ mons[month-1]+", "+ year);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+", "+ year);
             }
         }

         return status;
     }
     /**
      * This method is used to format datetime value passed as a parameter
      * in the format passed as an argument, with date suffix appended.
      * @param datetime datetime value passed for formatting
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted datetime value
      * 
      *     <pre>
      *         Example:
      *         2016 January, 12th; // for 'yyyymmdd' format
      *     </pre>
      * 
      */
     public static String getFormatLongDateTimeSuffix(String datetime, String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         int  hr = 0, min = 0, sec = 0, mil_sec = 0;
         StringTokenizer token = new StringTokenizer(datetime, " ");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String token_date = token.nextToken();
             String token_time = token.nextToken();

             StringTokenizer tokens = new StringTokenizer(token_date, "-");
             if(tokens.countTokens() == 0){
                 throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
             }else{
                 year = Integer.parseInt(tokens.nextToken());
                 month = Integer.parseInt(tokens.nextToken());
                 day = Integer.parseInt(tokens.nextToken());
             }
             StringTokenizer tokens2 = new StringTokenizer(token_time, ":");
             if(tokens2.countTokens() == 0){
                 throw new NoSuchElementException("Invalid time object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
             }else{
                 hr = Integer.parseInt(tokens2.nextToken());
                 min = Integer.parseInt(tokens2.nextToken());
                 String secs = tokens2.nextToken();
             }
             String phase = "";
             String mins = "";
             if(hr == 12){
                phase = "PM";
             }else if (hr > 12){
                 hr = hr - 12;
                 phase = "PM";
             }else{
                 phase = "AM";
             }
             if(min < 10){
                 mins = "0"+String.valueOf(min);
             }else{
                 mins = String.valueOf(min);
             }
//             String[] mon_index = {"1","2","3","4","5","6","7","8","9","10","11","12"};
             String[] mons = {"January","February","March","April","May","June","July","August","September","October","November","December"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};

             for(int i = 0; i < days.length; i++){
                 if(day == days[i]){
//                     System.out.println("Suffix: "+ suffix[i]);
                     suf = suffix[i];
                     break;
                 }
             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day+suf+ " " + hr+":"+mins+" "+phase);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+suf+ " "+ mons[month-1]+", "+ year+ " " + hr+":"+mins+" "+phase);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+suf+", "+ year+ " " + hr+":"+mins+" "+phase);
             }
         }

      return status;
     }
     /**
      * This method is used to format datetime value passed as a parameter
      * in the format passed as an argument, without date suffix appended.
      * @param datetime datetime value passed for formatting
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted datetime value 
      * 
      *     <pre>
      *         Example:
      *         2016 January, 12th; // for 'yyyymmdd' format
      *     </pre>
      * 
      */
     public static String getFormatLongDateTime(String datetime, String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         int  hr = 0, min = 0, sec = 0, mil_sec = 0;
         StringTokenizer token = new StringTokenizer(datetime, " ");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String token_date = token.nextToken();
             String token_time = token.nextToken();

             StringTokenizer tokens = new StringTokenizer(token_date, "-");
             if(tokens.countTokens() == 0){
                 throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
             }else{
                 year = Integer.parseInt(tokens.nextToken());
                 month = Integer.parseInt(tokens.nextToken());
                 day = Integer.parseInt(tokens.nextToken());
             }
             StringTokenizer tokens2 = new StringTokenizer(token_time, ":");
             if(tokens2.countTokens() == 0){
                 throw new NoSuchElementException("Invalid time object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
             }else{
                 hr = Integer.parseInt(tokens2.nextToken());
                 min = Integer.parseInt(tokens2.nextToken());
                 String secs = tokens2.nextToken();
             }
             String phase = "";
             String mins = "";
             if(hr == 12){
                phase = "PM";
             }else if (hr > 12){
                 hr = hr - 12;
                 phase = "PM";
             }else{
                 phase = "AM";
             }
             if(min < 10){
                 mins = "0"+String.valueOf(min);
             }else{
                 mins = String.valueOf(min);
             }
//             String[] mon_index = {"1","2","3","4","5","6","7","8","9","10","11","12"};
             String[] mons = {"January","February","March","April","May","June","July","August","September","October","November","December"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
//             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
//                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};
//
//             for(int i = 0; i < days.length; i++){
//                 if(day == days[i]){
////                     System.out.println("Suffix: "+ suffix[i]);
//                     suf = suffix[i];
//                     break;
//                 }
//             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day+ " " + hr+":"+mins+" "+phase);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+ " "+ mons[month-1]+", "+ year+ " " + hr+":"+mins+" "+phase);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+", "+ year+ " " + hr+":"+mins+" "+phase);
             }
         }

      return status;
     }
     /**
      * This method is used to format datetime value passed as a parameter
      * in the format passed as an argument, with date suffix appended.
      * @param datetime datetime value passed for formatting
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted date value with an abbreviated month spelt
      * 
      *     <pre>
      *         Example:
      *         2016 January, 12th; // for 'yyyymmdd' format
      *     </pre>
      * 
      */
     public static String getFormatShortDateTimeSuffix(String datetime, String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         int  hr = 0, min = 0, sec = 0, mil_sec = 0;
         StringTokenizer token = new StringTokenizer(datetime, " ");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String token_date = token.nextToken();
             String token_time = token.nextToken();

             StringTokenizer tokens = new StringTokenizer(token_date, "-");
             if(tokens.countTokens() == 0){
                 throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
             }else{
                 year = Integer.parseInt(tokens.nextToken());
                 month = Integer.parseInt(tokens.nextToken());
                 day = Integer.parseInt(tokens.nextToken());
             }
             StringTokenizer tokens2 = new StringTokenizer(token_time, ":");
             if(tokens2.countTokens() == 0){
                 throw new NoSuchElementException("Invalid time object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
             }else{
                 hr = Integer.parseInt(tokens2.nextToken());
                 min = Integer.parseInt(tokens2.nextToken());
                 String secs = tokens2.nextToken();
             }
             String phase = "";
             String mins = "";
             if(hr == 12){
                phase = "PM";
             }else if (hr > 12){
                 hr = hr - 12;
                 phase = "PM";
             }else{
                 phase = "AM";
             }
             if(min < 10){
                 mins = "0"+String.valueOf(min);
             }else{
                 mins = String.valueOf(min);
             }
//             String[] mon_index = {"1","2","3","4","5","6","7","8","9","10","11","12"};
             String[] mons = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};

             for(int i = 0; i < days.length; i++){
                 if(day == days[i]){
//                     System.out.println("Suffix: "+ suffix[i]);
                     suf = suffix[i];
                     break;
                 }
             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day+suf+ " " + hr+":"+mins+" "+phase);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+suf+ " "+ mons[month-1]+", "+ year+ " " + hr+":"+mins+" "+phase);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+suf+", "+ year+ " " + hr+":"+mins+" "+phase);
             }
         }

      return status;
     }
     /**
      * This method is used to format datetime value passed as a parameter
      * in the format passed as an argument, without date suffix appended.
      * @param datetime datetime value passed for formatting
      * @param format contains 3 format options ('yyyymmdd','mmddyyyy','ddmmyyyy')
      * @return returns a formatted date value with an abbreviated month spelt
      * 
      *     <pre>
      *         Example:
      *         2016 January, 12th; // for 'yyyymmdd' format
      *     </pre>
      * 
      */
     public static String getFormatShortDateTime(String datetime, String format){
         String status = "";
         String suf = "";
         int  year = 0, month = 0, day = 0;
         int  hr = 0, min = 0, sec = 0, mil_sec = 0;
         StringTokenizer token = new StringTokenizer(datetime, " ");
         if(token.countTokens() == 0){
             throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
         }else{
             String token_date = token.nextToken();
             String token_time = token.nextToken();

             StringTokenizer tokens = new StringTokenizer(token_date, "-");
             if(tokens.countTokens() == 0){
                 throw new NoSuchElementException("Invalid Date object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
             }else{
                 year = Integer.parseInt(tokens.nextToken());
                 month = Integer.parseInt(tokens.nextToken());
                 day = Integer.parseInt(tokens.nextToken());
             }
             StringTokenizer tokens2 = new StringTokenizer(token_time, ":");
             if(tokens2.countTokens() == 0){
                 throw new NoSuchElementException("Invalid time object parameter passsed. Check that the Date object passed as parameter is not null and it is in the right format.");
             }else{
                 hr = Integer.parseInt(tokens2.nextToken());
                 min = Integer.parseInt(tokens2.nextToken());
                 String secs = tokens2.nextToken();
             }
             String phase = "";
             String mins = "";
             if(hr == 12){
                phase = "PM";
             }else if (hr > 12){
                 hr = hr - 12;
                 phase = "PM";
             }else{
                 phase = "AM";
             }
             if(min < 10){
                 mins = "0"+String.valueOf(min);
             }else{
                 mins = String.valueOf(min);
             }
//             String[] mon_index = {"1","2","3","4","5","6","7","8","9","10","11","12"};
             String[] mons = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};

             int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
//             String[] suffix = {"st","nd","rd","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th","th"
//                                ,"st","nd","rd","th","th","th","th","th","th","th","st"};
//
//             for(int i = 0; i < days.length; i++){
//                 if(day == days[i]){
////                     System.out.println("Suffix: "+ suffix[i]);
//                     suf = suffix[i];
//                     break;
//                 }
//             }
             if(format.equalsIgnoreCase("yyyymmdd")){
                 status = String.valueOf(year + " " + mons[month-1]+", "+day+ " " + hr+":"+mins+" "+phase);
             }else if(format.equalsIgnoreCase("ddmmyyyy")){
                 status = String.valueOf(day+ " "+ mons[month-1]+", "+ year+ " " + hr+":"+mins+" "+phase);
             }else if(format.equalsIgnoreCase("mmddyyyy")){
                status = String.valueOf(mons[month-1]+" "+day+", "+ year+ " " + hr+":"+mins+" "+phase);
             }
         }

      return status;
     }
     /**
      * This method can be used to get valid number of days left between the current date and that from 
      * the database based on the given condition. if implements the DATEDIFF function of the sql query. 
      * It then calls the <code>getCustomFieldValue(query, conn)</code> method of the DataAccessObjcet in 
      * the DAO package to retrieve the String value which is then parsed and returned as int.
      * 
      * @param conditionCol represents the column name in the WHERE clause used to fetch the date value 
      * @param conditionVal represents the value criteria for the column name that returns the date value
      * @param column represents the date value field/column to be returned.
      * @param table represents the database table name
      * @param konn represents the connection object
      * @return the total number of days left
      */
    public static int getExpirationValidityDaysLeft(String conditionCol, String conditionVal, String column, String table, Connection konn){
        int days;
        String current_date = DateTime.getCurrentDate();
        String query = "select DATEDIFF('"+current_date+"', "+column+") from "+table+" where "+conditionCol+" = '"+conditionVal+"'";  
        String dac = DataAccessObject.getCustomFieldValue(query, konn);
        days = Integer.parseInt(dac);
         
        return days;
    }
    /**
     * This method implements the SQL DATE_SUB function between the datetime argument and the 
     * specified number of day|month|year|hour|minute|seconds depending on the format specified. 
     * It then calls the <code>getCustomFieldValue(query, conn)</code> method of the DataAccessObjcet in 
     * the DAO package to retrieve the String value.
     * 
     * @param datetime represents the datetime String object passed for processing.
     * @param num represents the total number of days|month|year|hour|min|sec to sub
     * @param format represents the SQL DATE_SUB format
     * @param connect represents the connection object
     * @return a String value of the datetime
     */
    public static String getDateTimeSub(String datetime, int num, String format, Connection connect){
        String days;

        String query = "select DATE_SUB('"+datetime+"', INTERVAL "+num+" "+format+")";  
        days = DataAccessObject.getCustomFieldValue(query, connect);
         
        return days;
    }
    /**
     * This method implements the SQL DATE_SUB function between the datetime argument and the 
     * specified number of day|month|year depending on the format specified. 
     * It then calls the <code>getCustomFieldValue(query, conn)</code> method of the DataAccessObjcet in 
     * the DAO package to retrieve the String value.
     * 
     * @param date represents the date String object passed for processing.
     * @param num represents the total number of days|month|year|hour|min|sec to sub
     * @param format represents the SQL DATE_SUB format
     * @param connect represents the connection object
     * @return a String value of the date
     */
    public static String getDateSub(String date, int num, String format, Connection connect){
        String days;

        String query = "select DATE_SUB('"+date+"', INTERVAL "+num+" "+format+")";  
        days = DataAccessObject.getCustomFieldValue(query, connect);
         
        return days;
    }
    /**
     * This method can be used to days|months|years to a date and returns the new date. Hence, it 
     * is useful for determining expiry dates.
     * 
     * @param date represents the date object from which the new date will be calculated.
     * @param num represents the num of days|months|years to be added
     * @param format represents the format to determine whether it is days|months|years that will be added
     * @param connect represents the connection object.
     * @return the new calculated date value in String
     */
    public static String getExpiryDate(String date, int num, String format, Connection connect){
        String days;

        String query = "select DATE_ADD('"+date+"', INTERVAL "+num+" "+format+")";  
        days = DataAccessObject.getCustomFieldValue(query, connect);
         
        return days;
    }
    /**
     * This method can be used to days|months|years|hour|minute|seconds to a date and returns the new date. Hence, it 
     * is useful for determining expiry dates.
     * @param datetime represents the datetime object from which the new date will be calculated.
     * @param num represents the num of days|months|years|hour|minute|seconds to be added
     * @param format represents the format to determine whether it is days|months|years|hour|minute|seconds that will be added
     * @param connect represents the connection object.
     * @return the new calculated datetime value in String
     */
    public static String getExpiryDateTime(String datetime, int num, String format, Connection connect){
        String days;

        String query = "select DATE_ADD('"+datetime+"', INTERVAL "+num+" "+format+")";  
        days = DataAccessObject.getCustomFieldValue(query, connect);
         
        return days;
    }
    /**
     * This method is used to format the time argument into the 24 hour display time.
     * 
     * @param time represents the time String object to be formatted.
     * @return a String value of 24hr formatted time. e.g 13:30 PM
     */
    public static String get24HrTimeFormatByTime(String time){
        String stamp = "";
        
        int hr = DateTime.getHourOnly(time);
        int min = DateTime.getMinutesOnly(time);
        String suf = "AM";
        
        String hour = "";
        String mins = "";
        
        if(hr == 12){
            suf = "NOON";
        }else if(hr > 12){
            suf = "PM";
        }
        hour = String.valueOf(hr);
        if(min < 10){
            mins = "0"+String.valueOf(min);
        }else{
            mins = String.valueOf(min);
        }
        stamp = hour+":"+mins+" "+suf;
        
        return stamp;
    }
    /**
     * This method is used to format the time argument into the 12 hour display time.
     * 
     * @param time represents the time String object to be formatted.
     * @return a String value of 12hr formatted time. e.g 1:30 PM
     */
    public static String get12HrTimeFormatByTime(String time){
        String stamp = "";
        
        int hr = DateTime.getHourOnly(time);
        int min = DateTime.getMinutesOnly(time);
        String suf = "AM";
        
        String hour = "";
        String mins = "";
        
        if(hr == 12){
            suf = "NOON";
        }else if(hr > 12){
            suf = "PM";
            hr -= 12;
        }
        hour = String.valueOf(hr);
        if(min < 10){
            mins = "0"+String.valueOf(min);
        }else{
            mins = String.valueOf(min);
        }
        stamp = hour+":"+mins+" "+suf;
        
        return stamp;
    }
    /**
     * This method is used to format the datetime argument into the 24 hour display time.
     * 
     * @param datetime represents the datetime String object passed, containing time to be formatted.
     * @return a String value of 24hr formatted time from a datetime argument. e.g 13:30 PM
     */
    public static String get24HrTimeFormatByDateTime(String datetime){
        String stamp = "";
        
        StringTokenizer token = new StringTokenizer(datetime, " ");
        if(token.hasMoreTokens()){
            String date = token.nextToken();
            String time = token.nextToken();
            
            int hr = DateTime.getHourOnly(time);
            int min = DateTime.getMinutesOnly(time);
            String suf = "AM";

            String hour = "";
            String mins = "";

            if(hr == 12){
                suf = "NOON";
            }else if(hr > 12){
                suf = "PM";
            }
            hour = String.valueOf(hr);
            if(min < 10){
                mins = "0"+String.valueOf(min);
            }else{
                mins = String.valueOf(min);
            }
            stamp = hour+":"+mins+" "+suf;
        }
        
        return stamp;
    }
    /**
     * This method is used to format the time argument into the 12 hour display time.
     * 
     * @param datetime represents the datetime String object passed, containing time to be formatted.
     * @return a String value of 12hr formatted time from a datetime argument. e.g 1:30 PM
     */
    public static String get12HrTimeFormatByDateTime(String datetime){
        String stamp = "";
        
        StringTokenizer token = new StringTokenizer(datetime, " ");
        if(token.hasMoreTokens()){
            String date = token.nextToken();
            String time = token.nextToken();
            
            int hr = DateTime.getHourOnly(time);
            int min = DateTime.getMinutesOnly(time);
            String suf = "AM";

            String hour = "";
            String mins = "";

            if(hr == 12){
                suf = "NOON";
            }else if(hr > 12){
                suf = "PM";
                hr -= 12;
            }
            hour = String.valueOf(hr);
            if(min < 10){
                mins = "0"+String.valueOf(min);
            }else{
                mins = String.valueOf(min);
            }
            stamp = hour+":"+mins+" "+suf;
        }
        
        return stamp;
    }

}
