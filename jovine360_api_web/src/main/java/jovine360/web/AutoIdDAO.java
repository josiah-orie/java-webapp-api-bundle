/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jovine360.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.WebRowSet;

/**
 *
 * @author OMEN
 * <p>
 * This class enables you to generate first and next ID number automatically for mostly 
 * the primary key fields in the the database. It contains different methods that ultimately 
 * connect to the database checks the last records or ID submitted and generate the next 
 * for submission. It also can be used for specialized ID generation for didferent category of 
 * records. 
 * </p>
 * <p>
 * For instance, players' record from different teams stored in the players table with 
 * players' id from each team uniquely auto gnerated from the other.
 * </p>
 * <pre>
 *      E.g: 
 *      Team A: ORM001, 0RM002,...
 *      Team B: SRM001, SRM002,...
 * </pre>
 */
public class AutoIdDAO {

    /**
     * This method is used to generate the next ID with a user-defined prefix for 
     * a database record. In the event of a first record, it returns "[PREFIX]01" as 
     * first ID. 
     * @param column represents the column that stores the auto id generated.
     * @param prefix represents the prefix appended to the auto id
     * @param total represents the total length of the auto id to be generated
     * @param table represents the database table.
     * @param connect represents the connection object
     * @return a String of ID value with the prefix provided.
     */
    public static String getNextAutoIDByCount(String column, String prefix, String total, String table, Connection connect) {
        String status = "";
        String query = "select COUNT(" + column + ") from " + table + "";
        WebRowSet dac = null;
        Connection conn = connect;
        dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                String id = dac.getString(1);
                if(id.equals("0")){
                    int lens = Integer.parseInt(total);
                    int prefix_len = prefix.length();
                    int len = lens - prefix_len - 1 ;
                    
                    String ids = "";
                    for (int i = 0; i < len; i++) {
                        ids += "0";
                    }
                    status = prefix + ids+"1";
                }else{
                    status = new AutoIdDAO().getNextID(id, prefix, total);
                } 
            } else {
                int lens = Integer.parseInt(total);
                int prefix_len = prefix.length();
                int len = lens - prefix_len - 1 ;

                String ids = "";
                for (int i = 0; i < len; i++) {
                    ids += "0";
                }
                status = prefix + ids+"1";
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * <p>
     * This method is used to generate the next ID with a user-defined prefix for 
     * a database record. In the event of a first record, it returns "[PREFIX]01" as 
     * first ID. 
     * </p>
     * <p>
     * This method is used when the table contains records that are categorized by 
     * another field and you want to generate next auto id for each category.
     * </p>
     * @param column represents the column that stores the auto id generated.
     * @param prefix represents the prefix appended to the auto id
     * @param total represents the total length of the auto id to be generated
     * @param table represents the database table.
     * @param connect represents the connection object
     * @return a String of ID value with the prefix provided.
     */
    public static String getNextUniqueAutoIDByCount(String column, String prefix, String total, String table, Connection connect) {
        String status = "";
        String query = "select COUNT(" + column + ") from " + table + " where "+column+" LIKE '"+prefix+"%'";
        WebRowSet dac = null;
        Connection conn = connect;
        dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                String id = dac.getString(1);
                if(id.equals("0")){
                    int lens = Integer.parseInt(total);
                    int prefix_len = prefix.length();
                    int len = lens - prefix_len - 1 ;
                    
                    String ids = "";
                    for (int i = 0; i < len; i++) {
                        ids += "0";
                    }
                    status = prefix + ids+"1";
                }else{
                    status = new AutoIdDAO().getNextID(id, prefix, total);
                } 
                
            } else {
                int lens = Integer.parseInt(total);
                int prefix_len = prefix.length();
                int len = lens - prefix_len - 1 ;

                String ids = "";
                for (int i = 0; i < len; i++) {
                    ids += "0";
                }
                status = prefix + ids+"1";
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method returns the total number of records.
     * @param column represents the column that stores the auto id generated.
     * @param table represents the database table.
     * @param connect represents the connection object
     * @return an total number of records as an integer value
     */
    public static int getTotalRecords(String column, String table, Connection connect) {
        int status = -1;
        String query = "select COUNT(" + column + ") from " + table + "";
        WebRowSet dac = null;
        Connection conn = connect;
        dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                String id = dac.getString(1);
                status = Integer.parseInt(id);
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method returns total unique number of all records with IDs starting with the prefix argument.
     * 
     * @param column represents the column that stores the auto id generated.
     * @param prefix represents the prefix appended to the auto id
     * @param table represents the database table.
     * @param connect represents the connection object
     * @return the integer value of the total number of records based on the criteria
     */
    public static int getTotalUniqueRecords(String column, String table, String prefix, Connection connect) {
        int status = -1;
        String query = "select COUNT(" + column + ") from " + table + " where "+column+" LIKE '"+prefix+"%'";
        WebRowSet dac = null;
        Connection conn = connect;
        dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                String id = dac.getString(1);
                status = Integer.parseInt(id);
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method returns total unique number of records with IDs starting with the prefix argument.
     * 
     * @param column represents the column that stores the auto id generated.
     * @param prefix represents the prefix appended to the auto id
     * @param table represents the database table.
     * @param connect represents the connection object
     * @return the integer value of the total number of records based on the criteria
     */
    public static int getUniqueTotalRecords(String column, String prefix, String table, Connection connect) {
        int status = -1;
        String query = "select COUNT(" + column + ") from " + table + " where "+column+" LIKE '"+prefix+"%'";
        WebRowSet dac = null;
        Connection conn = connect;
        dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                String id = dac.getString(1);
                status = Integer.parseInt(id);
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method returns the next auto id by retrieving the last ID submitted and
     * incrementing it. 
     * @param column represents the column that stores the auto id generated.
     * @param prefix represents the prefix appended to the auto id
     * @param total represents the total length of the auto id to be generated
     * @param table represents the database table.
     * @param connect represents the connection object
     * @return a String value of the next auto id
     */
    public static String getNextAutoID(String column, String prefix, String total, String table, Connection connect) {
        String status = "";
        String query = "select " + column + " from " + table + " order by("+column+") desc";
        WebRowSet dac = null;
        Connection conn = connect;
        dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                String lastid = dac.getString(1);
                String lasts = "";
                
                int prefix_len = prefix.length();
                int start_pos = prefix_len;
                int last_pos= lastid.length();
                String last_id_digit = lastid.substring(start_pos, last_pos);
                int last = Integer.parseInt(last_id_digit);
                int total_records = getTotalRecords(column, table, conn);
                if(total_records > 0){
                    if(last < total_records){
                        last = total_records;                        
                    }
                }
                lasts = String.valueOf(last);
                status = new AutoIdDAO().getNextID(lasts, prefix, total);
            } else {
//                status = prefix + "0001";
                status = new AutoIdDAO().getStartID("1", prefix, total);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method returns the next auto id for the different ID differentiated by 
     * their prefix, by retrieving the last ID submitted and incrementing it. 
     * 
     * @param column represents the column that stores the auto id generated.
     * @param prefix represents the prefix appended to the auto id
     * @param total represents the total length of the auto id to be generated
     * @param table represents the database table.
     * @param connect represents the connection object
     * @return a String value of the next auto id
     */
    public static String getNextUniqueAutoID(String column, String prefix, String total, String table, Connection connect) {
        String status = "";
        String query = "select " + column + " from " + table + " where "+column+" LIKE '"+prefix+"%' order by("+column+") desc";
        WebRowSet dac = null;
        Connection conn = connect;
        dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                String lastid = dac.getString(1);
                String lasts = "";
                
                int prefix_len = prefix.length();
                int start_pos = prefix_len;
                int last_pos= lastid.length();
                String last_id_digit = lastid.substring(start_pos, last_pos);
                int last = Integer.parseInt(last_id_digit);
                int total_records = getUniqueTotalRecords(column, prefix, table, conn);
                if(total_records > 0){
                    if(last < total_records){
                        last = total_records;                        
                    }
                }
                
                lasts = String.valueOf(last);
                status = new AutoIdDAO().getNextID(lasts, prefix, total);
            } else {
//                status = prefix + "0001";
                status = new AutoIdDAO().getStartID("1", prefix, total);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method returns the next auto id by retrieving the last ID submitted and
     * incrementing it.It also allow you to specify and pass the first value to be used.
     * 
     * @param column represents the column that stores the auto id generated.
     * @param prefix represents the prefix appended to the auto id
     * @param firstValue represents the first value to be returned in the event no record as been inserted already.
     * @param table represents the database table.
     * @param connect represents the connection object
     * @return a String value of the next auto id
     */
    public static String getNextAutoIDWithFirstValue(String column, String prefix, String firstValue, String table, Connection connect) {
        String status = "";
        String total = String.valueOf(firstValue.length());
        String query = "select " + column + " from " + table + " order by("+column+") desc";
        WebRowSet dac = null;
        Connection conn = connect;
        dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                String lastid = dac.getString(1);
                String lasts = "";
                
                int prefix_len = prefix.length();
                int start_pos = prefix_len;
                int last_pos= lastid.length();
                String last_id_digit = lastid.substring(start_pos, last_pos);
                int last = Integer.parseInt(last_id_digit);
                int total_records = getTotalRecords(column, table, conn);
                if(total_records > 0){
                    if(last < total_records){
                        last = total_records;                        
                    }
                }
                lasts = String.valueOf(last);
                status = new AutoIdDAO().getNextID(lasts, prefix, total);
            } else {
                status = firstValue;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method returns the next auto id for the different ID differentiated by 
     * their prefix, by retrieving the last ID submitted and incrementing it.It 
     * also allow you to specify and pass the first value to be used.
     * 
     * @param column represents the column that stores the auto id generated.
     * @param prefix represents the prefix appended to the auto id
     * @param firstValue represents the first value to be returned in the event no record as been inserted already.
     * @param table represents the database table.
     * @param connect represents the connection object
     * @return a String value of the next auto id
     */
    public static String getNextUniqueAutoIDWithFirstValue(String column, String prefix, String firstValue, String table, Connection connect) {
        String status = "";
        String total = String.valueOf(firstValue.length());
        String query = "select " + column + " from " + table + " where "+column+" LIKE '"+prefix+"%' order by("+column+") desc";
        WebRowSet dac = null;
        Connection conn = connect;
        dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                String lastid = dac.getString(1);
                String lasts = "";
                
                int prefix_len = prefix.length();
                int start_pos = prefix_len;
                int last_pos= lastid.length();
                String last_id_digit = lastid.substring(start_pos, last_pos);
                int last = Integer.parseInt(last_id_digit);
                int total_records = getUniqueTotalRecords(column, prefix, table, conn);
                if(total_records > 0){
                    if(last < total_records){
                        last = total_records;                        
                    }
                }
                lasts = String.valueOf(last);
                status = new AutoIdDAO().getNextID(lasts, prefix, total);
            } else {
                status = firstValue;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method returns the next auto id by retrieving the last ID submitted and
     * incrementing it. This method uses the count function to guess the last id 
     * submitted It also allow you to specify and pass the first value to be used.
     * 
     * @param column represents the column that stores the auto id generated.
     * @param prefix represents the prefix appended to the auto id
     * @param firstValue represents the first value to be returned in the event no record as been inserted already.
     * @param table represents the database table.
     * @param connect represents the connection object
     * @return a String value of the next auto id
     */
    public static String getNextAutoIDWithFirstValueByCount(String column, String prefix, String firstValue, String table, Connection connect) {
        String status = "";
        String query = "select COUNT(" + column + ") from " + table + "";
        WebRowSet dac = null;
        Connection conn = connect;
        dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                String id = dac.getString(1);
                if(id.equals("0")){
                    status = firstValue;
                }else{
                    status = new AutoIdDAO().getNextID(id, prefix, String.valueOf(id.length()));
                }              
            } else {
                status = firstValue;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method returns the next auto id for the records starting with the 
     * prefix, by retrieving the last ID submitted and incrementing it.It 
     * also allow you to specify and pass the first value to be used.
     * 
     * <p>
     * This method uses the COUNT function to determine the last ID value. Then 
     * processes it for increment to ascertain the next.
     * </p>
     * 
     * @param column represents the column that stores the auto id generated.
     * @param prefix represents the prefix appended to the auto id
     * @param firstValue represents the first value to be returned in the event no record as been inserted already.
     * @param table represents the database table.
     * @param connect represents the connection object
     * @return a String value of the next auto id
     */
    public static String getNextUniqueAutoIDWithFirstValueByCount(String column, String prefix, String firstValue, String table, Connection connect) {
        String status = "";
        String query = "select COUNT(" + column + ") from " + table + " where "+column+" LIKE '"+prefix+"%'";
        WebRowSet dac = null;
        Connection conn = connect;
        dac = DataAccessObject.loadCustomRecord(query, conn);
        try {
            if (dac.next()) {
                String id = dac.getString(1);
                if(id.equals("0")){
                    status = firstValue;
                }else{
                    status = new AutoIdDAO().getNextID(id, prefix, String.valueOf(id.length()));
                } 
            } else {
                status = firstValue;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This is used to fetch the last ID in the record.
     * 
     * @param tableName represents the database table.
     * @param column represents the column that stores the auto id generated.
     * @param conn represents the connection object
     * @return a String value of the next auto id
     */
    public static String getLastID(String tableName, String column, Connection conn) {
        String id = "";
        ResultSet rs = null;
        Statement st = null;
        Connection con = conn;
        String query = "select " + column + " from " + tableName + " order by(" + column + ") desc";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                id = rs.getString(column);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return id;
    }
    /**
     * This method is used when you manually intend to get the next by incrementing by 1. 
     * The "id" argument is passed which is then increment and returned as a String.
     * 
     * @param id represents the 'id' to increment to next value
     * @return a String value of the next ID
     */
    public static String getNextIDNoPrefix(String id) {
        Long id_val = Long.parseLong(id);
        id_val = id_val + 1;
        return String.valueOf(id_val);
    }
    /**
     * This method is used to auto generate the next id with the prefix. It is 
     * implemented by all other methods to get the next id.
     * 
     * @param id represents the last id in the record.
     * @param prefix represents the prefix to append to the id.
     * @param total_len the total length of the id.
     * @return a String value of the next auto id
     * 
     * <pre>
     * For Example:
     * If the id (the last id) is 23, and the prefix is 'PRE' and the total_len is 7
     * the next id = PRE0024
     * </pre>
     */
    private String getNextID(String id, String prefix, String total_len) {
        String ids = "";
        String val = "";
        val = id;
        int id_val = Integer.parseInt(val);
        id_val++;
        
        int prefix_len = prefix.length();
        int total_id_len = Integer.parseInt(total_len);
        int total = total_id_len - prefix_len;
        
        String id_new = String.valueOf(id_val);
        int id_len = id_new.length();
        if (id_len < total) {
            int zero = total - 1;
            String zeros = "";
            for (int i = 0; i < zero; i++) {
                zeros += "0";
            }
            ids = prefix+zeros+id_val;
        }else {
            ids = prefix+id_val;
        }
        
        return ids;
    }
    /**
     * This is used to auto generate the first id value.
     * 
     * @param id repressent the first id number
     * @param prefix represents the prefix to append to the id.
     * @param total_len the total length of the id.
     * @return a string of the formatted auto ID with prefix
     */
    private String getStartID(String id, String prefix, String total_len) {
        String ids = "";
        String val = "";
        val = id;
        int id_val = Integer.parseInt(val);
//        id_val++;
        
        int prefix_len = prefix.length();
        int total_id_len = total_len.length();
        int total = total_id_len - prefix_len;
        
        String id_new = String.valueOf(id_val);
        int id_len = id_new.length();
        if (id_len < total) {
            int zero = total - 1;
            String zeros = "";
            for (int i = 0; i < zero; i++) {
                zeros += "0";
            }
            ids = prefix+zeros+id_val;
        }else {
            ids = prefix+id_val;
        }
        
        return ids;
    }
    /**
     * This method is used to delete a record from the table using the column auto id generated
     * @param table represents the database table.
     * @param column represents the condtiona column name.
     * @param value represents the condtiona column value.
     * @param conn represents the connection object
     * @return returns 0 for successful deletion or -1 for failure of execution.
     */
    public static int deleteRecord(String table, String column, String value, Connection conn) {
        int status = -1;
        PreparedStatement pa = null;
        String query = "delete from ? where ? = ?";
        Connection con = conn;
        try {
            pa = con.prepareStatement(query);
            pa.setString(1, table);
            pa.setString(2, column);
            pa.setString(3, value);
            status = pa.executeUpdate();
            if (status == 1) {
                status = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutoIdDAO.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
}
