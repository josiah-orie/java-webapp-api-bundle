/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jovine360.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.WebRowSet;

/**
 *
 * @author OMEN
 */
public class CheckDAO {

    /**
     * This method is used to validate a new record by checking whether a record already exist.
     * @param query represents the user defined custome query to be executed.
     * @param connection represents the connection object.
     * @return 0 if record is found, 1 if not found and -1 if connection to the table could not be established.
     */
    public static int check(String query, Connection connection) {
        int status = -1;
        WebRowSet dac = DataAccessObject.loadCustomRecord(query, connection);
        try {
            if (dac.next()) {
                status = 0;
            } else {
                status = 1;
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method is used to validate a new record by checking whether a record 
     * already exist. It uses a single condition value as check parameter.
     * @param columnName represent the condition column name.
     * @param columnval represent the condition column value.
     * @param table represent the database table name
     * @param con1 represents the connection object.
     * @return 0 if record is found, 1 if not found and -1 if connection to the table could not be established.
     */
    public static int check(String columnName, String columnval, String table, Connection con1) {
        int status = -1;
        String query = "select * from " + table + " where " + columnName + " = '" + columnval + "'";
        WebRowSet dac = DataAccessObject.loadCustomRecord(query, con1);
        try {
            if (dac.next()) {
                status = 0;
            } else {
                status = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method is used to validate a new record by checking whether a record 
     * already exist. It uses a single condition value as check parameter.
     * @param columnName represent the condition column name.
     * @param columnval represent the condition column value.
     * @param table represent the database table name
     * @param konn represents the connection object.
     * @return 0 if record is found, 1 if not found and -1 if connection to the table could not be established.
     */
    public static int checkSecured(String columnName, String columnval, String table, Connection konn) {
        Connection conn = konn;
        int status = -1;
        String query = "select * from " + table + " where " + columnName + " = '" + columnval + "'";
        WebRowSet dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                status = 0;
            } else {
                status = 1;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
}
