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
public class ShowDataBean {
    
      private int table_record_id;
     private String tablename;

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public int getTable_record_id() {
        return table_record_id;
    }

    public void setTable_record_id(int table_record_id) {
        this.table_record_id = table_record_id;
    }
     
}
