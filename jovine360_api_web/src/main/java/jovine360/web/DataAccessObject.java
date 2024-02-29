/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools #~ Templates
 * and open the template in the editor.
 */
package jovine360.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

/**
 *
 * @author JOSIAH ORIE
 */
public class DataAccessObject{

    /**
     * This is the constructor which throws an SQLException
     * @throws SQLException it throws SQLException for unsuccessful database connection or sql syntax error
     */
    public DataAccessObject() throws SQLException{

    }
    private static String column; // a declaration of String column
    private static String value; // a declaration of String value
    private static String query; // a declaration of String query
    StringBuilder string = new StringBuilder();
    StringBuilder strings = new StringBuilder();
    /**
     * <p>This is used to set field values based on the number of fields within an entity, in case of complete table insertion or 
     * specified selected fields required. This method is accessed or invoked using the instance of the class.</p>
     * <p>
     * It is used with the insert, bulk-insert and update methods. It is used 
     * along with the <code>setColumnNames(String...columns)</code> method to set the 
     * values for all the columns added. As a result, must match the number of 
     * parameters in the <code>setColumnNames(String...columns)</code> method. 
     * </p>
     * <p>
     * It is <strong>USED ONLY</strong> when you are inserting into all the fields 
     * and using the <code>insertSecuredRecord(String tableName, Connection conn)</code> 
     * and <code>insertRecord(String tableName, Connection conn)</code> methods
     * </p>
     * E.g: <pre>
     *          DataAccessObject obj = new DataAccessObject();<br>
     *          obj.setColumnValues(val1,val2,...);
     *      </pre>
     * @param values represents the column values
     */
    public void setColumnValues(String... values) {
        String colValue = "";
        for (String val : values) {
            colValue = colValue + val + "#~";
        }
        colValue = colValue.substring(0, colValue.length() - 1);
        setValue(colValue);
    }
    /**
     * <p>This is used to set field names based on the number of fields within an entity, in case of complete table insertion or 
    * specified selected fields required. This method is accessed or invoked using the instance of the class.</p>
    * <p>
    * It is used with the insert, bulk-insert and update methods. It is always used with the <code>setColumnValues(String... values)</code>
    * </p>
    * E.g: <pre>
    *          DataAccessObject obj = new DataAccessObject();<br>
    *          obj.setColumnNames(val1,val2,...);
    *      </pre>
     * @param columns represents the columns
     */
    public void setColumnNames(String... columns) {
        String colums = "";
        for (String col : columns) {
            colums = colums + col + "#~";
        }
        colums = colums.substring(0, colums.length() - 1);
        setColumn(colums);
    }
    /**
     * This method is used to set multiple records values to bulk insert.
     * <p>
     * when used with the <code>bulkSelectedInsert(String tableName, Connection connect)</code>, 
     * the <code>setColumnNames(String...columns)</code> method is also used to specify the 
     * selected columns to bulk insert. For example:
     * </p>
     * <pre>
     *      obj.setColumnNames(col1, col2, col3);
     *      obj.setBulkValues(val1, val2, val3);
     *      obj.setBulkValues(val1, val2, val3);
     *      obj.setBulkValues(val1, val2, val3);
     *      bulkSelectedInsert(String tableName, Connection connect)
     * </pre>
     * 
     * <p>
     * However, when used with <code>bulkInsert(String tableName, Connection connect)</code> method 
     * columm names setting is not required. For instance:
     * </p>
     * <pre>
     *      obj.setBulkValues(val1, val2, val3);
     *      obj.setBulkValues(val1, val2, val3);
     *      obj.setBulkValues(val1, val2, val3);
     *      bulkInsert(String tableName, Connection connect)
     * </pre>
     * 
     * @param values represents the record values 
     */
    public void setBulkValues(String... values) {
        String bulkVals = "";
        String colValues = "";
        for (String val : values) {
            bulkVals = bulkVals + "'" + val + "',";
        }
        String bulk = bulkVals.substring(0, bulkVals.length() - 1);
        this.strings.append("(");
        this.strings.append(bulk);
        this.strings.append("),");
        setValue(String.valueOf(this.strings).substring(0, String.valueOf(this.strings).length() - 1));
    }
    /**
     * This method is used for implementing transactional(commit/rollback) operations. 
     * It is used to set multiple custom queries for execution, and used with the 
     * <code>batchRecord(Connection conn)</code> method.
     * @param query represents user defined query
     */
    public void setQuerys(String query) {
        this.string.append(query);
        this.string.append(';');
        setQuery(String.valueOf(this.string));
    }
    /**
     * 
     * @return querys set for execution
     */
    private static String getQuery() {
        return query;
    }
    /**
     * sets custom query for execution
     * @param query represents queries to be set
     */
    private static void setQuery(String query) {
        DataAccessObject.query = query;
    }
    /**
     * 
     * @return columns set execution
     */
    private static String getColumn() {
        return column;
    }
    /**
     * sets columns for execution
     * @param column represents columns to be set
     */
    private static void setColumn(String column) {
        DataAccessObject.column = column;
    }
    /**
     * 
     * @return set column values for execution
     */
    private static String getValue() {
        return value;
    }
    /**
     * set values for execution
     * @param value record values to be set.
     */
    private static void setValue(String value) {
        DataAccessObject.value = value;
    }
    /**
     * This method is used to execute batch custom sql queries. It works with the 
     * <code>setQuerys(String query)</code> method.
     * 
     * @param conn repressent the connection object
     * @return 0 - for a successful transactional operation.
     *         1 - for a unsuccessful transactinal operation.
     *        -1 - for an unsucessful database connection.
     * <pre>
     *  E.g:
     *  obj.setQuerys(query1);
     *  obj.setQuerys(query2);
     *  obj.setQuerys(query3);
     *  int val = batchRecord(conn);
     * </pre>
     */
    public static int batchRecord(Connection conn) {
        int status = -1;
        String querys = getQuery();
        querys = querys.substring(0, querys.length() - 1);
        PreparedStatement ps = null;
        String queri = "";
        StringTokenizer token = new StringTokenizer(querys, ";");
        try {
            conn.setAutoCommit(false);
            while (token.hasMoreTokens()) {
                queri = token.nextToken();
                ps = conn.prepareStatement(queri);
                status = ps.executeUpdate();
            }
            conn.commit();
            if (status == 1) {
                status = 0;
            }else{
                status = 1;
            }
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method is used to insert record to all the the fields in the table. 
     * It works with the <code>setColumnValues(String...values)</code> method.
     * <pre>
     *  E.g:
     *  obj.setColumnValues(val1, val2, val3, val4, val5,...);
     *  int res = insertRecord(tableName, connect);
     * </pre>
     * @param tableName specify table name
     * @param conn represent the connection object.
     * @return 0 - for a successful operation.
     *        -1 - for an unsucessful database connection.
     *
     */
    public static int insertRecord(String tableName, Connection conn) {
        int status = -1;
        String insert_vals = "";
        String values = getValue();
        StringTokenizer token = new StringTokenizer(values, "#~");
        while (token.hasMoreTokens()) {
            insert_vals = insert_vals + "'" + token.nextToken() + "',";
        }
        String vals = insert_vals.substring(0, insert_vals.length() - 1);
        String querys = "insert into " + tableName + " values(" + vals + ")";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(querys);
            status = ps.executeUpdate();
            if (status == 1) {
                status = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        } finally {
            if (ps != null && conn != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
            }
        }
        return status;
    }
    /**
     * This method is used to insert record to all the the fields in the table. 
     * This method implements escape parameter to hold each record values. Hence, 
     * more secured. It works with the <code>setColumnValues(String...values)</code> 
     * method.
     * <pre>
     *  E.g:
     *  obj.setColumnValues(val1, val2, val3, val4, val5,...);
     *  int res = insertSecuredRecord(tableName, connect);
     * </pre>
     * 
     * @param tableName specify table name
     * @param conn represent the connection object.
     * @return 0 - for a successful operation.
     *        -1 - for an unsuccessful database connection.
     */
    public static int insertSecuredRecord(String tableName, Connection conn) {
        int status = -1;
        String values = getValue();
        StringTokenizer token = new StringTokenizer(values, "#~");
        int val_num = token.countTokens();
        String esc = "";
        for (int i = 0; i < val_num; i++) {
            esc = esc + "?,";
        }
        String esc_val = esc.substring(0, esc.length() - 1);
        String querys = "insert into " + tableName + " values(" + esc_val + ")";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(querys);
            String vl = "";
            for (int j = 1; j <= val_num; j++) {
                vl = token.nextToken();
                ps.setString(j, vl);
            }
            status = ps.executeUpdate();
            if (status == 1) {
                status = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method is used to insert record to all the fields in the table. 
     * This method implements escape parameter to hold each record values. Hence, 
     * more secured. It works with the <code>setColumnValues(String...values)</code> 
     * method.
     * <pre>
     *  E.g:
     *  obj.setColumnValues(val1, val2, val3, val4, val5,...);
     *  int res = insertSecuredRecord(tableName, connect);
     * </pre>
     * 
     * @param tableName specify table name
     * @param conn represent the connection object.
     * @return 0 - for a successful operation.
     *        -1 - for an unsuccessful database connection.
     *         1 - for columns count does not harmonize with values count
     */
    public static int insertSelectedRecord(String tableName, Connection conn) {
        int status = -1;
        String insert_vals = "";
        String insert_cols = "";
        String values = getValue();
        String columns = getColumn();
        StringTokenizer token = new StringTokenizer(values, "#~");
        StringTokenizer tokens = new StringTokenizer(columns, "#~");
        if (token.countTokens() == tokens.countTokens()) {
            while (token.hasMoreTokens()) {
                insert_vals = insert_vals + "'" + token.nextToken() + "',";
                insert_cols = insert_cols + tokens.nextToken() + ",";
            }
            String vals = insert_vals.substring(0, insert_vals.length() - 1);
            String cols = insert_cols.substring(0, insert_cols.length() - 1);
            String querys = "insert into " + tableName + "(" + cols + ") values(" + vals + ")";
            PreparedStatement ps = null;
            try {
                ps = conn.prepareStatement(querys);
                status = ps.executeUpdate();
                if (status == 1) {
                    status = 0;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
            } finally {
                if (ps != null && conn != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
                    }
                }
            }
        } else {
            status = 1;
        }
        return status;
    }
    /**
     * This method is used to insert selected fields record to all the fields in the table. 
     * This method implements escape parameter to hold each record values. Hence, 
     * more secured. It works with the <code>setColumnNames(String...columns)</code> 
     * and <code>setColumnValues(String...values)</code> 
     * methods.
     * 
     * <pre>
     *  obj.setColumnNames(col1, col2, col3, col4, col5,...);
     *  obj.setColumnValues(val1, val2, val3, val4, val5,...);
     *  int res = insertSecuredSelectedRecord(tableName, connect);
     * </pre>
     * 
     * @param tableName represents the table name
     * @param conn represents the connection object
     * @return 
     *  -1: could not establish connection with database
     *  1:  columns count does not harmonize with values count
     *  0: data successfully committed/inserted to database
     */
    public static int insertSecuredSelectedRecord(String tableName, Connection conn) {
        int status = -1;
        String insert_cols = "";
        String values = getValue();
        String columns = getColumn();
        StringTokenizer token = new StringTokenizer(values, "#~");
        StringTokenizer tokens = new StringTokenizer(columns, "#~");
        if (token.countTokens() == tokens.countTokens()) {
            int val_num = token.countTokens();
            String esc = "";
            for (int i = 0; i < val_num; i++) {
                esc = esc + "?,";
            }
            String esc_val = esc.substring(0, esc.length() - 1);
            while (tokens.hasMoreTokens()) {
                insert_cols = insert_cols + tokens.nextToken() + ",";
            }
            String cols = insert_cols.substring(0, insert_cols.length() - 1);
            String querys = "insert into " + tableName + "(" + cols + ") values(" + esc_val + ")";
            PreparedStatement ps = null;
            try {
                ps = conn.prepareStatement(querys);
                String vl = "";
                for (int j = 1; j <= val_num; j++) {
                    vl = token.nextToken();
                    ps.setString(j, vl);
                }
                status = ps.executeUpdate();
                if (status == 1) {
                    status = 0;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
            } finally {
                if (ps != null && conn != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
                    }
                }
            }
        } else {
            status = 1;
        }
        return status;
    }
    /**
     * This method is used to update database record. It uses the 
     * <code>setColumnNames(String...columns)</code> 
     * and <code>setColumnValues(String...values)</code> methods to set the columns/
     * fields being updated.
     * 
     * <pre>
     *  obj.setColumnNames(col1, col2, col3, col4, col5,...);
     *  obj.setColumnValues(val1, val2, val3, val4, val5,...);
     *  int res = updateRecord(conditionColumn, conditionColumnValue, tableName, connect);
     * </pre>
     * 
     * 
     * @param conditionColumn specify the condition column name that identifies the record to be updated.
     * @param conditionValue specify the value for the condition column name
     * @param tableName specifies the table name
     * @param conn represents the connection
     * @return 
     * -1: could not establish connection with database
     *  1:  columns count does not harmonize with values count
     *  0: data successfully updated to database
     */
    public static int updateRecord(String conditionColumn, String conditionValue, String tableName, Connection conn) {
        int status = -1;
        PreparedStatement ps = null;
        String colName = getColumn();
        String columnVals = getValue();
        String col_values = "";
        StringTokenizer token = new StringTokenizer(colName, "#~");
        StringTokenizer tokens = new StringTokenizer(columnVals, "#~");
        if (token.countTokens() == tokens.countTokens()) {
            int val_num = tokens.countTokens();
            while (token.hasMoreTokens()) {
                col_values = col_values + token.nextToken() + " = '" + tokens.nextToken() + "', ";
            }
            String col_value = col_values.substring(0, col_values.length() - 2);
            String querys = "update " + tableName + " set " + col_value + " where " + conditionColumn + " = '" + conditionValue + "'";
            try {
                ps = conn.prepareStatement(querys);
                status = ps.executeUpdate();
                if (status == 1) {
                    status = 0;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
        } else {
            status = 1;
        }
        return status;
    }
    /**
     * This method is used to update database record. It uses the 
     * <code>setColumnNames(String...columns)</code> 
     * and <code>setColumnValues(String...values)</code> methods to set the columns/
     * fields being updated. This method is more secured as it implements the escape 
     * parameter to set the values.
     * 
     * <pre>
     *  obj.setColumnNames(col1, col2, col3, col4, col5,...);
     *  obj.setColumnValues(val1, val2, val3, val4, val5,...);
     *  int res = updateSecuredRecord(conditionColumn, conditionColumnValue, tableName, connect);
     * </pre>
     * 
     * 
     * @param conditionColumn specify the condition column name that identifies the record to be updated.
     * @param conditionValue specify the value for the condition column name
     * @param tableName specifies the table name
     * @param conn represents the connection
     * @return 
     * -1: could not establish connection with database
     *  1:  columns count does not harmonize with values count
     *  0: data successfully committed to database
     */
    public static int updateSecuredRecord(String conditionColumn, String conditionValue, String tableName, Connection conn) {
        int status = -1;
        PreparedStatement ps = null;
        String colName = getColumn();
        String columnVals = getValue();
        String col_values = "";
        StringTokenizer token = new StringTokenizer(colName, "#~");
        StringTokenizer tokens = new StringTokenizer(columnVals, "#~");
        if (token.countTokens() == tokens.countTokens()) {
            int val_num = tokens.countTokens();
            while (token.hasMoreTokens()) {
                col_values = col_values + token.nextToken() + " = ?, ";
            }
            String col_value = col_values.substring(0, col_values.length() - 2);
            String querys = "update " + tableName + " set " + col_value + " where " + conditionColumn + " = '" + conditionValue + "'";
            try {
                ps = conn.prepareStatement(querys);
                String vl = "";
                for (int i = 1; i <= val_num; i++) {
                    vl = tokens.nextToken();
                    ps.setString(i, vl);
                }
                status = ps.executeUpdate();
                if (status == 1) {
                    status = 0;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
        } else {
            status = 1;
        }
        return status;
    }
    /**
     * This method is used to update database record. It uses the 
     * <code>setColumnNames(String...columns)</code> 
     * and <code>setColumnValues(String...values)</code> methods to set the columns/
     * fields being updated.
     * 
     * <pre>
     *  String mulCondColumn = "conditionCol1,"+"conditionCol2,...";
     *  String mulCondValue = "val1,"+"val2,...";
     *  obj.setColumnNames(col1, col2, col3, col4, col5,...);
     *  obj.setColumnValues(val1, val2, val3, val4, val5,...);
     *  int res = updateRecordMulCond(mulCondColumn, mulCondValue, tableName, connect);
     * </pre>
     * 
     * @param mulCondColumn represents a String of concatenated multiple condition columns
     * @param mulCondValue represents a String of concatenated multiple condition columns values
     * @param tableName represents the table name
     * @param conn represents the connection
     * @return 
     * -1: could not establish connection with database
     * -2: condition columns count does not harmonize with condition values count
     * -3: columns count does not harmonize with values count
     *  0: data successfully committed to database
     */
    public static int updateRecordMulCond(String mulCondColumn, String mulCondValue, String tableName, Connection conn) {
        int status = -1;
        PreparedStatement ps = null;
        String colName = getColumn();
        String columnVals = getValue();
        String col_values = "";
        String columns = colName.substring(0, colName.length());
        String colvals = columnVals.substring(0, columnVals.length());
        StringTokenizer token = new StringTokenizer(columns, "#~");
        StringTokenizer tokens = new StringTokenizer(colvals, "#~");
        if (token.countTokens() == tokens.countTokens()) {
            while (token.hasMoreTokens()) {
                col_values = col_values + token.nextToken() + " = '" + tokens.nextToken() + "', ";
            }
            String col_value = col_values.substring(0, col_values.length() - 2);
            StringTokenizer tok = new StringTokenizer(mulCondColumn, ",");
            StringTokenizer toks = new StringTokenizer(mulCondValue, ",");
            String condition = "";
            if (tok.countTokens() == toks.countTokens()) {
                while (tok.hasMoreTokens()) {
                    condition = condition + tok.nextToken() + " = '" + toks.nextToken() + "' and ";
                }
                condition = condition.substring(0, condition.length() - 5);
                String querys = "update " + tableName + " set " + col_value + " where " + condition;
                try {
                    ps = conn.prepareStatement(querys);
                    status = ps.executeUpdate();
                    if (status == 1) {
                        status = 0;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
            } else {
                status = -3;
            }
        } else {
            status = -2;
        }
        return status;
    }
    /**
     * This method is used to update database record. It uses the 
     * <code>setColumnNames(String...columns)</code> 
     * and <code>setColumnValues(String...values)</code> methods to set the columns/
     * fields being updated. 
     * <p>
     * This method is more secured as it implements the escape 
     * parameter to set the values.
     * </p>
     * 
     * <pre>
     *  String mulCondColumn = "conditionCol1,"+"conditionCol2,...";
     *  String mulCondValue = "val1,"+"val2,...";
     *  obj.setColumnNames(col1, col2, col3, col4, col5,...);
     *  obj.setColumnValues(val1, val2, val3, val4, val5,...);
     *  int res = updateSecuredRecordMulCond(mulCondColumn, mulCondValue, tableName, connect);
     * </pre>
     * 
     * @param mulCondColumn represents a String of concatenated multiple condition columns
     * @param mulCondValue represents a String of concatenated multiple condition columns values
     * @param tableName represents the table name
     * @param conn represents the connection
     * @return 
     * -1: could not establish connection with database
     * -2: condition columns count does not harmonize with condition values count
     * -3: columns count does not harmonize with values count
     *  0: data successfully committed to database
     */
    public static int updateSecuredRecordMulCond(String mulCondColumn, String mulCondValue, String tableName, Connection conn) {
        int status = -1;
        PreparedStatement ps = null;
        String colName = getColumn();
        String columnVals = getValue();
        String col_values = "";
        String columns = colName.substring(0, colName.length());
        String colvals = columnVals.substring(0, columnVals.length());
        StringTokenizer token = new StringTokenizer(columns, "#~");
        StringTokenizer tokens = new StringTokenizer(colvals, "#~");
        if (token.countTokens() == tokens.countTokens()) {
            int val_num = tokens.countTokens();
            while (token.hasMoreTokens()) {
                col_values = col_values + token.nextToken() + " = ?, ";
            }
            String col_value = col_values.substring(0, col_values.length() - 2);
            StringTokenizer tok = new StringTokenizer(mulCondColumn, ",");
            StringTokenizer toks = new StringTokenizer(mulCondValue, ",");
            String condition = "";
            if (tok.countTokens() == toks.countTokens()) {
                while (tok.hasMoreTokens()) {
                    condition = condition + tok.nextToken() + " = '" + toks.nextToken() + "' and ";
                }
                condition = condition.substring(0, condition.length() - 5);
                String querys = "update " + tableName + " set " + col_value + " where " + condition;
                try {
                    ps = conn.prepareStatement(querys);
                    String vl = "";
                    for (int i = 1; i <= val_num; i++) {
                        vl = tokens.nextToken();
                        ps.setString(i, vl);
                    }
                    status = ps.executeUpdate();
                    if (status >= 1) {
                        status = 0;
                    }else{
                        status = 1;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
            } else {
                status = -3;
            }
        } else {
            status = -2;
        }
        return status;
    }
    /**
     * This method is used to delete a record from the database.
     * 
     * @param tableName represents the table name
     * @param conn represents the connection
     * @param columnName represents the condition column name
     * @param value represents the condition column value 
     * @return  0 - for successful deletion
     *         -1 - for failure to delete record. Either bad connection or invalid record criteria
     */
    public static int deleteRecord(String tableName, Connection conn, String columnName, String value) {
        Connection konn = null;
        konn = conn;
        int status = -1;
        String querys = "delete from " + tableName + " where " + columnName + " = '" + value + "'";
        PreparedStatement ps = null;
        try {
            ps = konn.prepareStatement(querys);
            status = ps.executeUpdate();
            if (status == 1) {
                status = 0;
            }
            konn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method is used to make bulk insert records to the database.
     * <pre>
     *  obj.setBulkValues(val1, val2, val3);
     *  obj.setBulkValues(val1, val2, val3);
     *  obj.setBulkValues(val1, val2, val3);
     *  .
     *  .
     *  .
     *  bulkInsert(String tableName, Connection connect);
     * </pre>
     * 
     * @param tableName represents the table name
     * @param connect represents the connection
     * @return -1: could not establish connection with database
     *          0: data successfully committed/inserted to database
     */
    public static int bulkInsert(String tableName, Connection connect) {
        int status = -1;
        String values = getValue();
        String querys = "insert into " + tableName + " values" + values;
        PreparedStatement ps = null;
        Connection conn = connect;
        try {
            ps = conn.prepareStatement(querys);
            status = ps.executeUpdate();
            if (status != 0) {
                status = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method is used to make bulk insert records into selected fields of a table..
     * <pre>
     *      obj.setColumnNames(col1, col2, col3);
     *      obj.setBulkValues(val1, val2, val3);
     *      obj.setBulkValues(val1, val2, val3);
     *      obj.setBulkValues(val1, val2, val3);
     *      bulkSelectedInsert(String tableName, Connection connect);
     * </pre>
     * 
     * @param tableName represents the table name
     * @param connect represents the connection
     * @return -1: could not establish connection with database
     *          0: data successfully committed/inserted to database
     */
    public static int bulkSelectedInsert(String tableName, Connection connect) {
        int status = -1;
        String colu = getColumn();
        String values = getValue();
        StringTokenizer tokens = new StringTokenizer(colu, "#~");
        String insert_cols = "";
        while (tokens.hasMoreTokens()) {
            insert_cols = insert_cols + "" + tokens.nextToken() + ",";
        }
        String cols = insert_cols.substring(0, insert_cols.length() - 1);
        String querys = "insert into " + tableName + "(" + cols + ") values" + values;
        PreparedStatement ps = null;
        Connection conn = connect;
        try {
            ps = conn.prepareStatement(querys);
            status = ps.executeUpdate();
            if (status != 0) {
                status = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method is used to delete bulk records from the database.
     * 
     * <pre>
     *  int result = bulkDelete(keyColumn, table, connect, begin, end);
     * </pre>
     * 
     * @param primaryKey represents the primary key/ condition column
     * @param tableName represents the table name.
     * @param connect represents the connection object.
     * @param begin represents the start range value
     * @param end represents the end range value
     * @return -1: could not establish connection with database
     *          0: data successfully deleted to database
     */
    public static int bulkDelete(String primaryKey, String tableName, Connection connect, String begin, String end) {
        int status = -1;
        PreparedStatement ps = null;
        Connection conn = connect;
        String querys = "delete from " + tableName + " where " + primaryKey + " between '" + begin+"' and '"+end+"'";
        try {
            ps = conn.prepareStatement(querys);
            status = ps.executeUpdate();
            if (status != 0) {
                status = 0;
            }
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method is used to return the total number of records inserted into a table
     * 
     * @param columnName the column name used for counting.
     * @param tableName represents the table name
     * @param connnect represents the connection
     * @return total number of rows/records or 0 if table is empty
     */
    public static int getTableRowCount(String columnName, String tableName, Connection connnect) {
        int status = -1;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = connnect;
        String querys = "select COUNT(" + columnName + ") from " + tableName;
        try {
            ps = conn.prepareStatement(querys);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getInt(1);
            } else {
                status = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method is used to return the total number of records inserted into 
     * a table based on a satisfied criteria.
     * 
     * @param conditionColumn represents the condition column
     * @param conditionValue represents the condition column value
     * @param tableName represents the table name.
     * @param conn represent the connection object.
     * @return total number of rows/records or 0 if table is empty
     */
    public static int getDynaRowCount(String conditionColumn, String conditionValue, String tableName, Connection conn) {
        int status = -1;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String columnName = getColumn();
        String querys = "select COUNT(" + columnName + ") from " + tableName + " where " + conditionColumn + " = '" + conditionValue + "'";
        try {
            ps = conn.prepareStatement(querys);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getInt(1);
            } else {
                status = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method is used to return the total number of records inserted into 
     * a table. This method executes custom user defined query.
     * 
     * @param query represents query with the COUNT function to be executed.
     * @param conn represent the connection object.
     * @return total number of rows/records or 0 if table is empty
     */
    public static int getCustomRowCount(String query, Connection conn) {
        int status = -1;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return status;
    }
    /**
     * This method is used to populate all records from the database table.
     * 
     * <pre>
     *  E.g.:   WebRowSet dac = loadAllRecord(table, connect);
     * </pre>
     * 
     * @param tableName represents the table name.
     * @param connect represent the connection object.
     * @return a WebRowSet object containing all records.
     */
    public static WebRowSet loadAllRecord(String tableName, Connection connect) {
        WebRowSet dao = null;
        String querys = "select * from " + tableName;
        Connection conn = connect;
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();            
            dao.setCommand(querys);
            dao.execute(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * This method is used to populate a portion/limited records from the database table. 
     * It is designed to be used to implement PAGINATION.
     * 
     * <pre>
     *  E.g.:   WebRowSet dac = loadPaginatedAllRecord(offset, rowcount, table, connect);
     * </pre>
     * 
     * @param offset represents the start position of the record to be returned.
     * @param rowcount represents the total number of records to return
     * @param tableName represents the table name.
     * @param connect represent the connection object.
     * @return a WebRowSet object containing the specified number of records from the specified start position.
     */
    public static WebRowSet loadPaginatedAllRecord(String offset, String rowcount, String tableName, Connection connect) {
        WebRowSet dao = null;
        String querys = "select * from " + tableName + " limit " + offset + "," + rowcount;
        Connection conn = connect;
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(querys);
            dao.execute(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * This method is used to populate a sorted portion/limited records from the database table. 
     * It is designed to be used to implement PAGINATION.
     * 
     * <pre>
     *  E.g.:   WebRowSet dac = loadPaginatedAllRecordOrdered(columnName, offset, rowcount, table, connect, true);
     * </pre>
     * 
     * @param columnName represents the column to be sorted.
     * @param offset represents the start position of the record to be returned.
     * @param rowcount represents the total number of records to return
     * @param tableName represents the table name.
     * @param connect represent the connection object.
     * @param flag represents sort type. 'True' for 'asc' and 'False' for 'desc'
     * @return a sorted WebRowSet object containing the specified number of records from the specified start position.
     */
    public static WebRowSet loadPaginatedAllRecordSorted(String columnName, String offset, String rowcount, String tableName, Connection connect, Boolean flag) {
        WebRowSet dao = null;
        String querys = "";
        if (flag == true) {
            querys = "select * from " + tableName + " order by(" + columnName + ") asc limit " + offset + "," + rowcount;
        } else {
            querys = "select * from " + tableName + " order by(" + columnName + ") desc limit " + offset + "," + rowcount;
        }
        Connection conn = connect;
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(querys);
            dao.execute(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * This method is used to populate a portion/limited records from the database table. 
     * It is designed to be used to implement PAGINATION based on a criteria. 
     * It works with <code>setColumnNames(String...columns)</code> and 
     * <code>setColumnValues(String...values)</code>
     * 
     * <pre>
     *  E.g.:   
     *  obj.setColumnNames(col1, col2,...n);
     *  obj.setColumnValues(val1, val2,...n);
     *  WebRowSet dac = loadPaginatedDynaRecord(offset, rowcount, table, connect);
     * </pre>
     * 
     * @param offset represents the start position of the record to be returned.
     * @param rowcount represents the total number of records to return
     * @param tableName represents the table name.
     * @param connect represent the connection object.
     * @return a WebRowSet object containing the specified number of records from the specified start position.
     */
    public static WebRowSet loadPaginatedDynaRecord(String offset, String rowcount, String tableName, Connection connect) {
        WebRowSet dao = null;
        Connection conn = connect;
        String col_vals = "";
        String columns = getColumn();
        String values = getValue();
        StringTokenizer token = new StringTokenizer(columns, "#~");
        StringTokenizer valToken = new StringTokenizer(values, "#~");
        if (token.countTokens() == valToken.countTokens()) {
            while (token.hasMoreTokens()) {
                col_vals = col_vals + token.nextToken() + " = '" + valToken.nextToken() + "' and ";
            }
            String conditions = col_vals.substring(0, col_vals.length() - 4);
            String querys = "select * from " + tableName + " where " + conditions + " limit " + offset + "," + rowcount;
            try {
                dao = RowSetProvider.newFactory().createWebRowSet();
                dao.setCommand(querys);
                dao.execute(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
        } else {
            return dao;
        }
        return dao;
    }
    /**
     * This method is used to populate a sorted portion/limited records from the database table. 
     * It is designed to be used to implement PAGINATION based on a criteria. 
     * It works with <code>setColumnNames(String...columns)</code> and 
     * <code>setColumnValues(String...values)</code>
     * 
     * <pre>
     *  E.g.:   
     *  obj.setColumnNames(col1, col2,...n);
     *  obj.setColumnValues(val1, val2,...n);
     *  WebRowSet dac = loadPaginatedDynaRecordOrdered(column, offset, rowcount, table, connect);
     * </pre>
     * 
     * @param columnName represents the column to be sorted.
     * @param offset represents the start position of the record to be returned.
     * @param rowcount represents the total number of records to return
     * @param tableName represents the table name.
     * @param connect represent the connection object.
     * @param flag represents sort type. 'True' for 'asc' and 'False' for 'desc'
     * @return a sorted WebRowSet object containing the specified number of records from the specified start position.
     */
    public static WebRowSet loadPaginatedDynaRecordOrdered(String columnName, String offset, String rowcount, String tableName, Connection connect, Boolean flag) {
        WebRowSet dao = null;
        Connection conn = connect;
        String col_vals = "";
        String columns = getColumn();
        String values = getValue();
        StringTokenizer token = new StringTokenizer(columns, "#~");
        StringTokenizer valToken = new StringTokenizer(values, "#~");
        if (token.countTokens() == valToken.countTokens()) {
            while (token.hasMoreTokens()) {
                col_vals = col_vals + token.nextToken() + " = '" + valToken.nextToken() + "' and ";
            }
            String conditions = col_vals.substring(0, col_vals.length() - 4);
            String querys = "";
            if (flag == true) {
                querys = "select * from " + tableName + " where " + conditions + " order by(" + columnName + ") asc limit " + offset + "," + rowcount;
            } else {
                querys = "select * from " + tableName + " where " + conditions + " order by(" + columnName + ") desc limit " + offset + "," + rowcount;
            }
            try {
                dao = RowSetProvider.newFactory().createWebRowSet();
                dao.setCommand(querys);
                dao.execute(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
        } else {
            return dao;
        }
        return dao;
    }
    /**
     * This method is used to populate or get all record and sorts the record.
     * 
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @param sortbyColumn the column to be sorted
     * @param value represents the sort type 'True' for 'asc' and 'False' for 'desc'
     * @return a sorted WebRowSet object containing all records.
     */
    public static WebRowSet loadAllSortedRecord(String tableName, Connection connect, String sortbyColumn, Boolean value) {
        WebRowSet dao = null;
        String values = "";
        if (value) {
            values = "asc";
        } else {
            values = "desc";
        }
        String querys = "select * from " + tableName + " order by(" + sortbyColumn + ") " + values;
        Connection conn = connect;
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(querys);
            dao.execute(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * This method is uaed to fetch selected records. As such it works with the 
     * <code>setColumnNames(String...column)</code> method to list the fields/columns to 
     * populate.
     * 
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @return a WebRowSet object containing all selected records.
     */
    public static WebRowSet loadSelectedRecord(String tableName, Connection connect) {
        WebRowSet dao = null;
        Connection conn = connect;
        String columns = getColumn();
        StringTokenizer token = new StringTokenizer(columns, "#~");
        String kolumns = "";
        while (token.hasMoreTokens()) {
            kolumns = kolumns + token.nextToken() + ", ";
        }
        kolumns = kolumns.substring(0, kolumns.length() - 2);
        String querys = "select " + kolumns + " from " + tableName;
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(querys);
            dao.execute(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * This method fetches sorted records of selected fields/columns to populate. 
     * As such it works with the <code>setColumnNames(String...column)</code>
     * method.
     * 
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @param sortbyColumn the column to be sorted
     * @param value represents the sort type 'True' for 'asc' and 'False' for 'desc'
     * @return a sorted WebRowSet object containing all selected records.
     */
    public static WebRowSet loadSelectedSortedRecord(String tableName, Connection connect, String sortbyColumn, Boolean value) {
        WebRowSet dao = null;
        Connection conn = connect;
        String columns = getColumn();
        StringTokenizer token = new StringTokenizer(columns, "#~");
        String kolumns = "";
        while (token.hasMoreTokens()) {
            kolumns = kolumns + token.nextToken() + ", ";
        }
        kolumns = kolumns.substring(0, kolumns.length() - 2);
        String values = "";
        if (value) {
            values = "asc";
        } else {
            values = "desc";
        }
        String querys = "select " + kolumns + " from " + tableName + " order by(" + sortbyColumn + ") " + values;
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(querys);
            dao.execute(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * This method is used to return all records based on a satisfied condition. 
     * 
     * 
     * @param conditionColumn represents the condition column
     * @param conditionValue represents the condition column value
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @return a WebRowSet object containing all records based on the given condition.
     */
    public static WebRowSet loadAllDynaRecord(String conditionColumn, String conditionValue, String tableName, Connection connect) {
        WebRowSet dao = null;
        Connection conn = connect;
        String querys = "select * from " + tableName + " where " + conditionColumn + " = '" + conditionValue + "'";
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(querys);
            dao.execute(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * This method is used to return all records that is sorted and based on a 
     * satisfied condition.
     * 
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @param conditionCol represents the condition column
     * @param conditionVal represents the condition column value
     * @param sortbyColumn represents the sorted column.
     * @param value represents the sort type 'True' for 'asc' and 'False' for 'desc'
     * @return a sorted WebRowSet object containing all records based on the given condition.
     */
    public static WebRowSet loadAllSortedDynaRecord(String tableName, Connection connect, String conditionCol, String conditionVal, String sortbyColumn, Boolean value) {
        WebRowSet dao = null;
        Connection conn = connect;
        String ord = "";
        if (value) {
            ord = "asc";
        } else {
            ord = "desc";
        }
        String querys = "select * from " + tableName + " where " + conditionCol + " = '" + conditionVal + "' order by(" + sortbyColumn + ")" + ord;
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(querys);
            dao.execute(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * This method is used to return selected records that is based on a 
     * satisfied condition. Hence, it works with the <code>setColumnNames(String...column)</code> 
     * to list the fields to return.
     * 
     * @param conditionCol represents the condition column
     * @param conditionVal represents the condition column value
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @return a WebRowSet object containing selected records based on a given condition.
     */
    public static WebRowSet loadSelectedDynaRecord(String conditionCol, String conditionVal, String tableName, Connection connect) {
        WebRowSet dao = null;
        Connection conn = connect;
        String columns = getColumn();
        StringTokenizer token = new StringTokenizer(columns, "#~");
        String kolumns = "";
        while (token.hasMoreTokens()) {
            kolumns = kolumns + token.nextToken() + ", ";
        }
        kolumns = kolumns.substring(0, kolumns.length() - 2);
        String querys = "select " + kolumns + " from " + tableName + " where " + conditionCol + " = '" + conditionVal + "'";
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(querys);
            dao.execute(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * This method is used to return all records that is based on a 
     * satisfied condition. Hence, it works with the <code>setColumnNames(String...column)</code> 
     * and <code>setColumnValues(String...values)</code> methods to list the conditions;
     * 
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @return a WebRowSet object containing all records based on multiple columns conditions.
     */
    public static WebRowSet loadAllMulCondition(String tableName, Connection connect) {
        WebRowSet dao = null;
        Connection conn = connect;
        String col_vals = "";
        String columns = getColumn();
        String values = getValue();
        StringTokenizer token = new StringTokenizer(columns, "#~");
        StringTokenizer valToken = new StringTokenizer(values, "#~");
        if (token.countTokens() == valToken.countTokens()) {
            while (token.hasMoreTokens()) {
                col_vals = col_vals + token.nextToken() + " = '" + valToken.nextToken() + "' and ";
            }
            String conditions = col_vals.substring(0, col_vals.length() - 4);
            String querys = "select * from " + tableName + " where " + conditions;
            try {
                dao = RowSetProvider.newFactory().createWebRowSet();
                dao.setCommand(querys);
                dao.execute(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
        } else {
            return dao;
        }
        return dao;
    }
    /**
     * This method is used to return all records that is based on a 
     * satisfied condition. Hence, it works with the <code>setColumnNames(String...column)</code> 
     * and <code>setColumnValues(String...values)</code> methods to list the conditions;
     * 
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @param selectedColumn represents a list of all fields/columns to fetch records
     * @return a WebRowSet object containing selected records based on multiple columns condition.
     */
    public static WebRowSet loadSelectedMulCondition(String tableName, Connection connect, String... selectedColumn) {
        WebRowSet dao = null;
        Connection conn = connect;
        String col_vals = "";
        String col = "";
        String col_sel = "";
        for (String cols : selectedColumn) {
            col = col + cols + ", ";
        }
        col_sel = col.substring(0, col.length() - 2);
        String values = getValue();
        String columns = getColumn();
        StringTokenizer token = new StringTokenizer(columns, ",");
        StringTokenizer valToken = new StringTokenizer(values, ",");
        if (token.countTokens() == valToken.countTokens()) {
            while (token.hasMoreTokens()) {
                col_vals = col_vals + token.nextToken() + " = '" + valToken.nextToken() + "' and ";
            }
            String conditions = col_vals.substring(0, col_vals.length() - 4);
            String querys = "select " + col_sel + " from " + tableName + " where " + conditions;
            try {
                dao = RowSetProvider.newFactory().createWebRowSet();
                dao.setCommand(querys);
                dao.execute(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
        } else {
            return dao;
        }
        return dao;
    }
    /**
     * This method is used to return all records that is based on a 
     * satisfied condition. It implements the IN function. Hence, it works with the 
     * and <code>setColumnValues(String...values)</code> methods to list the condition 
     * columns multiple values.
     * 
     * @param conditionColumn represents the condition column.
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @return a WebRowSet object containing all records fetched based on the given condition.
     */
    public static WebRowSet loadAllMulCondValue(String conditionColumn, String tableName, Connection connect) {
        WebRowSet dao = null;
        Statement st = null;
        ResultSet rs = null;
        Connection conn = connect;
        String col_vals = "";
        String values = getValue();
        StringTokenizer valToken = new StringTokenizer(values, "#~");
        if (valToken.countTokens() == 2) {
            while (valToken.hasMoreTokens()) {
                col_vals = col_vals + "'" + valToken.nextToken() + "',";
            }
            String conditions = col_vals.substring(0, col_vals.length() - 1);
            String querys = "select * from " + tableName + " where " + conditionColumn + "IN(" + conditions + ")";
            try {
                dao = RowSetProvider.newFactory().createWebRowSet();
                dao.setCommand(querys);
                dao.execute(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
        } else {
            return dao;
        }
        return dao;
    }
    /**
     * his method is used to return selected records that is based on a 
     * satisfied condition. It implements the IN function. Hence, it works with the 
     * and <code>setColumnValues(String...values)</code> methods to list the condition 
     * columns multiple values.
     * 
     * @param conditionCol represents the condition column.
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @param selected_columns represents a list of all fields/columns to fetch records
     * @return a WebRowSet object containing selected records based on the given condition.
     */
    public static WebRowSet loadSelectedMulCondValue(String conditionCol, String tableName, Connection connect, String... selected_columns) {
        WebRowSet dao = null;
        Connection conn = connect;
        String col_vals = "";
        String values = getValue();
        String column_vals = "";
        for (String val : selected_columns) {
            column_vals = column_vals + val + ",";
        }
        column_vals = column_vals.substring(0, column_vals.length() - 1);
        StringTokenizer valToken = new StringTokenizer(values, "#~");
        if (valToken.countTokens() == 2) {
            while (valToken.hasMoreTokens()) {
                col_vals = col_vals + "'" + valToken.nextToken() + "',";
            }
            String conditions = col_vals.substring(0, col_vals.length() - 1);
            String querys = "select " + column_vals + " from " + tableName + " where " + conditionCol + "IN(" + conditions + ")";
            try {
                dao = RowSetProvider.newFactory().createWebRowSet();
                dao.setCommand(querys);
                dao.execute(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
        } else {
            return dao;
        }
        return dao;
    }
    /**
     * This method returns all records based on a group by criteria.
     * 
     * @param groupCol represents the group by column
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @return a sorted WebRowSet object containing all records that satisfy the GROUP BY function.
     */
    public static WebRowSet loadAllGroupBy(String groupCol, String tableName, Connection connect) {
        WebRowSet dao = null;
        Connection conn = connect;
        String querys = "select * from " + tableName + " group by(" + groupCol + ")";
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(querys);
            dao.execute(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * This method returns selected records based on a group by criteria. 
     * Hence, it works with the <code>setColumnNames(String...column)</code> 
     * to list the fields to return.
     * 
     * @param groupCol represents the group by column
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @return a sorted WebRowSet object containing selected records that satisfy the GROUP BY function.
     */
    public static WebRowSet loadSelectedGroupBy(String groupCol, String tableName, Connection connect) {
        WebRowSet dao = null;
        Connection conn = connect;
        String columns = getColumn();
        StringTokenizer token = new StringTokenizer(columns, "#~");
        String kolumns = "";
        while (token.hasMoreTokens()) {
            kolumns = kolumns + token.nextToken() + ", ";
        }
        kolumns = kolumns.substring(0, kolumns.length() - 2);
        String querys = "select " + kolumns + " from " + tableName + " group by(" + groupCol + ")";
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(querys);
            dao.execute(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * This method is used to execute any user defined custom query.
     * 
     * @param query represents user defined custome query.
     * @param connect represents database connection.
     * @return a WebRowSet object containing records from the executed query.
     */
    public static WebRowSet loadCustomRecord(String query, Connection connect) {
        WebRowSet dao = null;
        Connection conn1 = connect;
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(query);
            dao.execute(conn1);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return dao;
    }
    /**
     * <p>
     * This method is used to load the specified columns listed using the 
     * <code>setColumnNames(String...columns)</code> method with the first column
     * representing the 'key', and the rest, in the case two or more columns 
     * concatenated as the 'value'. 
     * </p>
     * <p>
     * This can be used to enforce referential integrity by loading existing records 
     * to a dropdown menu for selection purpose only.
     * </p>
     * 
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @param value indicates whether the key should be appended to the value or not for display
     * @return a LinkedHashMap object containing all records.
     */
    public static LinkedHashMap loadRecord(String tableName, Connection connect, Boolean value) {
        Connection conn = connect;
        LinkedHashMap link = new LinkedHashMap();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String col = getColumn();
        StringTokenizer tokens = new StringTokenizer(col, "#~");
        String kolumns = "";
        while (tokens.hasMoreTokens()) {
            kolumns = kolumns + tokens.nextToken() + ", ";
        }
        kolumns = kolumns.substring(0, kolumns.length() - 2);
        String querys = "select " + kolumns + " from " + tableName;
        StringTokenizer token = new StringTokenizer(col, "#~");
        int columnNum = token.countTokens();
        try {
            ps = conn.prepareStatement(querys);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = "";
                if (value == true) {
                    String str1 = rs.getString(1);
                    for (int j = 2; j <= columnNum; j++) {
                        name = name + rs.getString(j) + " ";
                    }
                    name = name.substring(0, name.length() - 1);
                    String names = str1 + ": " + name;
                    link.put(str1, names);
                    continue;
                }
                String id = rs.getString(1);
                for (int i = 2; i <= columnNum; i++) {
                    name = name + rs.getString(i) + " ";
                }
                name = name.substring(0, name.length() - 1);
                link.put(id, name);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return link;
    }
    /**
     * <p>
     * This method is used to load the specified columns listed using the 
     * <code>setColumnNames(String...columns)</code> method with the first column
     * representing the 'key', and the rest, in the case two or more columns 
     * concatenated as the 'value', based on a given criteria
     * </p>
     * <p>
     * This can be used to enforce referential integrity by loading existing records 
     * to a dropdown menu for selection purpose only.
     * </p>
     * 
     * @param conditionColumn represents the condition column
     * @param conditionValue represents the condition column value
     * @param tableName represents the table name
     * @param connect represents the database connection
     * @param value indicates whether the key should be appended to the value or not for display
     * @return a LinkedHashMap object containing all records based on a satisfied condition.
     */
    public static LinkedHashMap loadDynaRecord(String conditionColumn, String conditionValue, String tableName, Connection connect, Boolean value) {
        Connection conn = connect;
        LinkedHashMap link = new LinkedHashMap();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String colums = getColumn();
        StringTokenizer tokens = new StringTokenizer(colums, "#~");
        String kolumns = "";
        while (tokens.hasMoreTokens()) {
            kolumns = kolumns + tokens.nextToken() + ", ";
        }
        kolumns = kolumns.substring(0, kolumns.length() - 2);
        String querys = "select " + kolumns + " from " + tableName + " where " + conditionColumn + " = ?";
        try {
            ps = conn.prepareStatement(querys);
            ps.setString(1, conditionValue);
            rs = ps.executeQuery();
            StringTokenizer token = new StringTokenizer(colums, "#~");
            int columnNum = token.countTokens();
            while (rs.next()) {
                String name = "";
                if (value == true) {
                    String str1 = rs.getString(1);
                    for (int j = 2; j <= columnNum; j++) {
                        name = name + rs.getString(j) + " ";
                    }
                    name = name.substring(0, name.length() - 1);
                    String names = str1 + ": " + name;
                    link.put(str1, names);
                    continue;
                }
                String id = rs.getString(1);
                for (int i = 2; i <= columnNum; i++) {
                    name = name + rs.getString(i) + " ";
                }
                name = name.substring(0, name.length() - 1);
                link.put(id, name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return link;
    }
    /**
     * <p>
     * This method is used to load records based on a user-defined query with the first column
     * representing the 'key', and the rest, in the case two or more columns 
     * concatenated as the 'value', based on a given criteria
     * </p>
     * <p>
     * This can be used to enforce referential integrity by loading existing records 
     * to a dropdown menu for selection purpose only.
     * </p>
     * 
     * @param query represents the user defined query
     * @param value indicates whether the key should be appended to the value or not for display
     * @param conn represents the database connection
     * @return a LinkedHashMap object containing all records based on the executed query.
     */
    public static LinkedHashMap loadCustomRecords(String query, Boolean value, Connection conn) {
        LinkedHashMap link = new LinkedHashMap();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ResultSetMetaData res = rs.getMetaData();
                int num = res.getColumnCount();
                String name = "";
                if (value == true) {
                    String str1 = rs.getString(1);
                    for (int j = 2; j <= num; j++) {
                        name = name + rs.getString(j) + " ";
                    }
                    name = name.substring(0, name.length() - 1);
                    String names = str1 + ": " + name;
                    link.put(str1, names);
                    continue;
                }
                String id = rs.getString(1);
                for (int i = 2; i <= num; i++) {
                    name = name + rs.getString(i) + " ";
                }
                name = name.substring(0, name.length() - 1);
                link.put(id, name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return link;
    }
    
    /**
     * This method is used to get a single field value  based on a criteria and 
     * returns it as a String. 
     * 
     * @param column represents the column record to be populated
     * @param id represents the condition criteria column.
     * @param id_val represents the condition column value
     * @param tablename represents the table name
     * @param conn represent the connection object
     * @return a single field value as String
     */
    public static String getFieldValue(String column, String id, String id_val, String tablename, Connection conn) {
        String values = "";
        String querys = "select " + column + " from " + tablename + " where " + id + " = '" + id_val + "'";
        WebRowSet dac = null;
        PreparedStatement ps = null;
        dac = loadCustomRecord(querys, conn);
        try {
            if (dac.next()) {
                values = dac.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return values;
    }
    /**
     * This method is used to get a single field value and returns it as a String. 
     * it takes a custom user-defined query for execution.
     * 
     * @param query represents query to return a single column/field value.
     * @param connect represent the connection object
     * @return a single field value as String
     */
    public static String getCustomFieldValue(String query, Connection connect) {
        WebRowSet dao = null;
        String values = "";
        Connection conn = connect;
        try {
            dao = RowSetProvider.newFactory().createWebRowSet();
            dao.setCommand(query);
            dao.execute(conn);
            if (dao.next()) {
                values = dao.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return values;
    }
    /**
     * This is use to return single column records like all emails, etc
     * 
     * @param column represents the column record to be populated
     * @param id represents the condition criteria column.
     * @param id_val represents the condition column value
     * @param tablename represents the table name
     * @param conn represent the connection object
     * @return a WebrowSet object of all single column records.
     */
    public static WebRowSet loadFieldValues(String column, String id, String id_val, String tablename, Connection conn) {
        String querys = "select " + column + " from " + tablename + " where " + id + " = '" + id_val + "'";
        WebRowSet dac = null;
        dac = loadCustomRecord(querys, conn);
        return dac;
    }
    /**
     * This method is used to return the last ID value of the specified column.
     * 
     * @param tableName represents the table name.
     * @param column represents the column name.
     * @param conn represent the connection object
     * @return String value of the last column value
     */
    public static String getLastID(String tableName, String column, Connection conn) {
        String id = "";
        WebRowSet rs = null;
        Statement st = null;
        Connection con = conn;
        String querys = "select " + column + " from " + tableName + " order by(" + column + ") desc";
        try {
            rs = loadCustomRecord(querys, con);
            if (rs.next()) {
                id = rs.getString(column);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return id;
    }
    /**
     * This method is used to return the last ID column value. Where '*" is used 
     * in the query in place of the column, it will return the first field value name
     * 
     * @param querys represents the custom query to return the last id value.
     * @param conn represent the connection object
     * @return String value of the field
     */
    public static String getCustomLastID(String querys, Connection conn) {
        String id = "";
        WebRowSet rs = null;
        Statement st = null;
        Connection con = conn;
        try {
            rs = loadCustomRecord(querys, con);
            if (rs.next()) {
                id = rs.getString(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return id;
    }
}
