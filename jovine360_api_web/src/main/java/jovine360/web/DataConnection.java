/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jovine360.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOSIAHORIE
 * <p>
 * <code>DataConnection</code> is class that enables you to create a connection object. 
 * It contains a single method called <code>getConnection()</code> that returns a connection 
 * object. 
 * </p>
 * <p>
 * Note, that the connection class works with MySQL databases only. 
 * </p>
 */
public class DataConnection {
    /**
     * 
     * @param url represents the schema's url
     * @param username represent the username for database connection authentication
     * @param password represent the password for database connection authentication
     * @return the connection object
     */
    public static Connection getConnection(String url, String username, String password) {
        Connection conn = null;
        String urls = url;
        String usernames = username;
        String passwords = password;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urls, usernames, passwords);
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return conn;
    }
}
