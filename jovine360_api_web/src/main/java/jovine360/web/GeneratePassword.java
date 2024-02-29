/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jovine360.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OMEN
 */
public class GeneratePassword {
    /**
     * This method generates an alphanumeric code of length to the size passed as an argument.
     * @param size specifies the length in size of the password/token to be generated.
     * @return the generate password in String
     */
    public static String generatePassword(int size) {
        String password = "";
        String passwordPool = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            int index = rand.nextInt(62);
            password = password + passwordPool.charAt(index);
        }
        return password;
    }
    /**
     * This method generates an non-numeric code of length to the size passed as 
     * an argument. it can also contain special characters (!@?)
     * @param size specifies the length in size of the password/token to be generated.
     * @return the generate password.
     */
    public static String generateUniqueID(int size) {
        String password = "";
        String passwordPool = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@?";
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            int index = rand.nextInt(36);
            password = password + passwordPool.charAt(index);
        }
        return password;
    }
    /**
     * This method is used to determine that a unique otp or default code is generated for each table in the database
     * @param size specifies the length in size of the password/token to be generated.
     * @param tableName the table name to hold the unique code.
     * @param column represents the column name to store the code.
     * @param conn the connection object.
     * @return a String value of the unique code generated
     */
    public static String generateNextUniqueID(int size, String tableName, String column, Connection conn) {
        String id = "";
        String val = "";
        String query = "";
        Connection konn = conn;
        int check = 0;
        while (check == 0) {
            val = generateUniqueID(size);
            query = "select * from " + tableName + " where " + column + "='" + val + "'";
            check = CheckDAO.check(query, konn);
        }
        id = val;
        try {
            konn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GeneratePassword.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return id;
    }
    /**
     * This method is used to determine that a unique otp or default code is 
     * generated for each table in the database. This method appends a prefix to the code. 
     * The method ensures that an unused code is generated.
     * 
     * @param size specifies the length in size of the password/token to be generated.
     * @param prefix the prefix to append to the id 
     * @param tableName the table name to hold the unique code.
     * @param column represents the column name to store the code.
     * @param conn the connection object.
     * @return a String value of the unique code generated
     */
    public static String generateNextUniqueIDWithPrefix(int size, String prefix, String tableName, String column, Connection conn) {
        String id = "";
        String val = "";
        String query = "";
        Connection konn = conn;
        int check = 0;
        while (check == 0) {
            val = prefix + generateUniqueID(size);
            query = "select * from " + tableName + " where " + column + "='" + val + "'";
            check = CheckDAO.check(query, konn);
        }
        id = val;
        try {
            konn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GeneratePassword.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return id;
    }
    /**
     * This method is used to generate alphabetic code/password/pin of variable length determined by the size argument
     * @param size specifies the length in size of the password/token to be generated.
     * @return String value of numeric code
     */
    public static String generateStringPassword(int size) {
        String password = "";
        String passwordPool = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            int index = rand.nextInt(52);
            password = password + passwordPool.charAt(index);
        }
        return password;
    }
    /**
     * This method is used to generate numeric code password/pin of length determined 
     * by the size argument. size argument ranges from 1-10
     * @param size specifies the length in size of the password/token to be generated.
     * @return integer value of numeric code.
     */
    public static int generateIntPassword(int size) {
        String password = "";
        int pass = 0;
        String passwordPool = "0123456789";
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            int index = rand.nextInt(10);
            password = password + passwordPool.charAt(index);
        }
        if (size < 11) {
            pass = Integer.parseInt(password);
        } else {
            throw new NumberFormatException("Input size larger than integer size. Use method public static String generateLongPassword(int size) instead");
        }
        return pass;
    }
    /**
     * This method is used to generate numeric code password/pin of variable length determined by the size argument
     * @param size specifies the length in size of the password/token to be generated.
     * @return String value of numeric code
     */
    public static String generateLongPassword(int size) {
        String password = "";
        String passwordPool = "0123456789";
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            int index = rand.nextInt(10);
            password = password + passwordPool.charAt(index);
        }
        return password;
    }
}
