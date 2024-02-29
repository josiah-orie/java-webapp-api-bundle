/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jovine360.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOSIAHORIE
 * <code>CodeGen</code> is a class that contains methods that returns a generated unique reference
 * String code/number of alphanumeric values.
 * <p>
 *  <code>CodeGen</code> is part of a collection of api(s) under jovine_colletions_complete 
 * v2 api, design aid reducing development time and allow the developer to focus on the 
 * business logic of the application. 
 * </p>
 * <p>
 *  It can used to generate reference token using current Datetime with a random generated integer 
 *  and used for any database table. it works with our custom DateTime, GeneratePassword, and 
 *  CheckDAO Classes which are also part of the collection bundle api.
 * </p>
 */
public class CodeGen {

    /**
     * <p>
     * This method generate a reference code of String values using the current
     * date and time values and a randomly generated 4 integer values appended.
     * 'REF' prefix is appending at the beginning of the value.
     * </p>
     * @param table represents the Database table name that contains the reference code field
     * @param column represents the field/column name for the reference code.
     * @param conn represents a jdbc connection object to any database. Connection is closed after process.
     * @return a String of alphanumeric values in the format "REFYYYYMMDDHHmmSS####"
     * <pre>
     *      Example:
     *      String refcode = generateRefCode(tableName, columnName, connection);
     * </pre>
     */
    public static String generateRefCode(String table, String column, Connection conn) {
        String status = "";
        String flag = "no";
        String digit = "";
        String prefix = "REF";
        String datetime = "";
        
        String dateNtime = DateTime.getCurrentDateTime();
        int yr = DateTime.getYears(dateNtime);
        int mon = DateTime.getMonths(dateNtime);
        int dat = DateTime.getDays(dateNtime);
        
        int hr = DateTime.getHour(dateNtime); 
        int min = DateTime.getMinutes(dateNtime);
        int sec = DateTime.getSeconds(dateNtime);
        datetime = String.valueOf(yr)+String.valueOf(mon)+String.valueOf(dat)+String.valueOf(hr)+String.valueOf(min)+String.valueOf(sec);
        
        Connection con_check = conn;
        while (flag.equals("no")) {
            digit = String.valueOf(GeneratePassword.generateIntPassword(4));
            status = prefix + datetime + digit;
            String query = "select * from " + table + " where " + column + "='" + status + "'";
            int stat = CheckDAO.check(query, con_check);
            if (stat == 1) {
                flag = "yes";
                try {
                    con_check.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CodeGen.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
            }
        }
        return status;
    }
    /**
     * <p>
     * This method generate a reference code of String values using the current
     * date and time values and a randomly generated integer values appended, 
     * with length specified by the size parameter.
     * 'REF' prefix is appending at the beginning of the value.
     * </p>
     * @param size represents the length of the randomly generated integer values to be appended to the date time. Size can't be more than 10
     * @param table represents the Database table name that contains the reference code field
     * @param column represents the field/column name for the reference code.
     * @param conn represents a jdbc connection object to any database. Connection is closed after process.
     * @return a String of alphanumeric values in the format "REFYYYYMMDDHHmmSS####"
     * <pre>
     *      Example:
     *      String refcode = generateUniqueOrderCode(6, tableName, columnName, connection);
     * </pre>
     */
    public static String generateUniqueOrderCode(int size, String table, String column, Connection conn) {
        String status = "";
        String flag = "no";
        String digit = "";
        String prefix = "#";
        Connection con_check = conn;
        while (flag.equals("no")) {
            digit = String.valueOf(GeneratePassword.generateIntPassword(size));
            status = prefix + digit;
            String query = "select * from " + table + " where " + column + "='" + status + "'";
            int stat = CheckDAO.check(query, con_check);
            if (stat == 1) {
                flag = "yes";
                try {
                    con_check.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CodeGen.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
            }
        }
        return status;
    }
    /**
     * <p>
     * This method generate a reference code of String values using the current
     * date and time values and a randomly generated integer values appended, 
     * with length specified by the size parameter.
     * </p>
     * @param prefix this represents user-defined prefix 
     * @param size represents the length of the randomly generated integer values to be appended to the date time. Size can't be more than 10
     * @param table represents the Database table name that contains the reference code field
     * @param column represents the field/column name for the reference code.
     * @param conn represents a jdbc connection object to any database. Connection is closed after process.
     * @return a String of alphanumeric values in the format "REFYYYYMMDDHHmmSS####"
     * <pre>
     *      Example:
     *      String refcode = generateUniqueOrderCode([ABCabc], 6, tableName, columnName, connection);
     * </pre>
     */
    public static String generateUniqueCode(String prefix, int size, String table, String column, Connection conn) {
        String status = "";
        String flag = "no";
        String digit = "";
        Connection con_check = conn;
        while (flag.equals("no")) {
            digit = String.valueOf(GeneratePassword.generateIntPassword(size));
            status = prefix + digit;
            String query = "select * from " + table + " where " + column + "='" + status + "'";
            int stat = CheckDAO.check(query, con_check);
            if (stat == 1) {
                flag = "yes";
                try {
                    con_check.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CodeGen.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
            }
        }
        return status;
    }
    /**
     * <p>
     * This method generate a reference code of String values using the current
     * date and time values and a randomly generated integer values appended, 
     * with length specified by the size parameter.
     * </p>
     * @param prefix this represents user-defined prefix 
     * @param size represents the length(no limit) of the randomly generated integer values to be appended to the date time.
     * @param table represents the Database table name that contains the reference code field
     * @param column represents the field/column name for the reference code.
     * @param conn represents a jdbc connection object to any database. Connection is closed after process.
     * @return a String of alphanumeric values in the format "REFYYYYMMDDHHmmSS####"
     * <pre>
     *      Example:
     *      String refcode = generateLongUniqueCode([ABCabc], 6, tableName, columnName, connection);
     * </pre>
     */
    public static String generateLongUniqueCode(String prefix, int size, String table, String column, Connection conn) {
        String status = "";
        String flag = "no";
        String digit = "";
        Connection con_check = conn;
        while (flag.equals("no")) {
            digit = GeneratePassword.generateLongPassword(size);
            status = prefix + digit;
            String query = "select * from " + table + " where " + column + "='" + status + "'";
            int stat = CheckDAO.check(query, con_check);
            if (stat == 1) {
                flag = "yes";
                try {
                    con_check.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CodeGen.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
            }
        }
        return status;
    }
    /**
     * This method generates an code to be used as an One-time-Pin
     * @param size represents the length(no more than 10) of the randomly generated integer values.
     * @param table represents the Database table name that contains the reference code field
     * @param column represents the field/column name for the reference code.
     * @param conn represents a jdbc connection object to any database. Connection is closed after process.
     * @return an integer values
     * <pre>
     *      Example:
     *      String refcode = generateUniqueOTP(6, tableName, columnName, connection);
     * </pre>
     */
    public static String generateUniqueOTP(int size, String table, String column, Connection conn) {
        String status = "";
        String flag = "no";
        String digit = "";
        Connection con_check = conn;
        while (flag.equals("no")) {
            digit = String.valueOf(GeneratePassword.generateIntPassword(size));
            status = digit;
            String query = "select * from " + table + " where " + column + "='" + status + "'";
            int stat = CheckDAO.check(query, con_check);
            if (stat == 1) {
                flag = "yes";
                try {
                    con_check.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CodeGen.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
            }
        }
        return status;
    }
    /**
     * <p>
     * This method generate a reference code of String values using the current
     * date and time values and a randomly generated integer values appended, 
     * with length specified by the size parameter.
     * </p>
     * @param prefix this represents user-defined prefix 
     * @param size represents the length of the randomly generated integer values to be appended to the date time. Size can't be more than 10
     * @param table represents the Database table name that contains the reference code field
     * @param column represents the field/column name for the reference code.
     * @param conn represents a jdbc connection object to any database. Connection closure after process will be handled by implementation.
     * @return a String of alphanumeric values in the format "REFYYYYMMDDHHmmSS####"
     * <pre>
     *      Example:
     *      String refcode = generateOpenConnectionUniqueCode([ABCabc], 6, tableName, columnName, connection);
     * </pre>
     */
    public static String generateOpenConnectionUniqueCode(String prefix, int size, String table, String column, Connection conn) {
        String status = "";
        String flag = "no";
        String digit = "";
        Connection con_check = conn;
        while (flag.equals("no")) {
            digit = String.valueOf(GeneratePassword.generateIntPassword(size));
            status = prefix + digit;
            String query = "select * from " + table + " where " + column + "='" + status + "'";
            int stat = CheckDAO.check(query, con_check);
            if (stat == 1) {
                flag = "yes";
            }
        }
        return status;
    }
    /**
     * <p>
     * This method generate a reference code of String values using the current
     * date and time values and a randomly generated integer values appended, 
     * with length specified by the size parameter.
     * </p>
     * @param prefix this represents user-defined prefix 
     * @param size represents the length of the randomly generated integer values to be appended to the date time. Size can't be more than 10
     * @param table represents the Database table name that contains the reference code field
     * @param column represents the field/column name for the reference code.
     * @param conn represents a jdbc connection object to any database. Connection closure after process will be handled by implementation.
     * @return a String of alphanumeric values in the format "REFYYYYMMDDHHmmSS####"
     * <pre>
     *      Example:
     *      String refcode = generateUniqueIntCodeNewPrefix([ABCabc], 6, tableName, columnName, connection);
     * </pre>
     */
    public static String generateUniqueIntCodeNewPrefix(String prefix, int size, String table, String column, Connection conn){
        String status = "";
        String code = "";
//        String query = "select * from "+table+" where "+column+"= '"+code+"'";
        String flag = "no";
        do{
            code = prefix+GeneratePassword.generateIntPassword(size);
            int check = CheckDAO.checkSecured(column, code, table, conn);
            if(check == 1){
                flag = "yes";
                status = code;
            }
        }while(flag.equals("no"));
//        System.out.println("Result: "+ status);
        return status;
    }
    /**
     * This method is used to generate and return the timeStamp in String. 
     * @return a String value of a timestamp in the format YYYYMMDDHHmmSS
     */
    public static String getTimeStamp(){
        String status = "";
        String datetime = DateTime.getCurrentDateTime();
        StringTokenizer token = new StringTokenizer(datetime, " ");
        if(token.hasMoreTokens()){
            String date = token.nextToken();
            String time = token.nextToken();
            
            StringTokenizer token_date = new StringTokenizer(date, "-");
            StringTokenizer token_time = new StringTokenizer(time, ":");
            
            String year = null, month = null, day = null, hour = null, min = null, sec = null;
            if(token_date.hasMoreTokens()){
                year = token_date.nextToken();
                month = token_date.nextToken();
                day = token_date.nextToken();
            }
            if(token_time.hasMoreTokens()){
                hour = token_time.nextToken();
                min = token_time.nextToken();
                sec = token_time.nextToken();
            }
            status = year+month+day+hour+min+sec;
        }
        
        return status;
    }
}
