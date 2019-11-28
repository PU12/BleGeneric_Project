/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author DELL
 */
public class TableViewBean {
      private int table_record_id;
    private String tablename;
    private String column_list;
    private String sql_query;
    private String peripheral_list;

    public String getSql_query() {
        return sql_query;
    }

    public void setSql_query(String sql_query) {
        this.sql_query = sql_query;
    }
    
    

    public String getColumn_list() {
        return column_list;
    }

    public void setColumn_list(String column_list) {
        this.column_list = column_list;
    }

    public int getTable_record_id() {
        return table_record_id;
    }

    public String getTablename() {
        return tablename;
    }

  
    public void setTable_record_id(int table_record_id) {
        this.table_record_id = table_record_id;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getPeripheral_list() {
        return peripheral_list;
    }

    public void setPeripheral_list(String peripheral_list) {
        this.peripheral_list = peripheral_list;
    }

    
}
