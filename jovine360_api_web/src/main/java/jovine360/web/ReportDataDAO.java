/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jovine360.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josiah
 */
public class ReportDataDAO {
    /**
     * This method is used to return a field data from any database
     * @param returnColumn represents the column with the fetching data.
     * @param conditionColumn represents the conditional column
     * @param conditionValue represents the conditional column's value
     * @param table represents the table name
     * @param conn represents the connection.
     * @return a single field value of String object
     */
    public static String getFieldData(String returnColumn, String conditionColumn, String conditionValue, String table, Connection conn){
        String value = "";
        String query = "select "+returnColumn+" from "+table+" where "+conditionColumn+" = '"+conditionValue+"'";
        value = DataAccessObject.getCustomFieldValue(query, conn);
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportDataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return value;
    }
}
