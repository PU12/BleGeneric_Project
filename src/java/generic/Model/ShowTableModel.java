/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic.Model;

import Bean.TableViewBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author DELL
 */
public class ShowTableModel {
    
   private Connection connection;
    private String driverClass;
    private String connectionString;
    private String db_userName;
    private String db_userPasswrod;
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "lightyellow";
    private final String COLOR_ERROR = "red";

    public void setConnection() {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(connectionString, db_userName, db_userPasswrod);
        } catch (Exception e) {
            System.out.println("TableViewModel setConnection() Error: " + e);
        }
    }

    public int getNoOfRows() {
        int noOfRows = 0;
        try {
            ResultSet rset = connection.prepareStatement("select count(*) from table_record ").executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
        } catch (Exception e) {
            System.out.println("TableViewModel getNoOfRows() Error: " + e);
        }
        return noOfRows;
    }

    public List<TableViewBean> showData(int lowerLimit, int noOfRowsToDisplay) {
        List<TableViewBean> list = new ArrayList<TableViewBean>();
        
  String query = "SELECT * from table_record";       
try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                TableViewBean tableview = new  TableViewBean();
                tableview.setTable_record_id(rset.getInt("table_record_id"));
                tableview.setTablename(rset.getString("tablename"));
                tableview. setColumn_list(rset.getString("column_list"));
              
                list.add(tableview);
            }
        } catch (Exception e) {
            System.out.println("TableViewModel showData() Error: " + e);
        }
        return list;
    }
    
    
    
       public JSONArray showtable(String tablename) {
        //Map<Integer, Junction> junctionList = new HashMap<Integer, Junction>();
        JSONArray jsonArray = new JSONArray();
        try {
//            String query = " select column_list from table_record where tablename = '"+ tablename + "' ";
              String query = " select * from "+ tablename + " ";

            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                JSONObject jsonObj = new JSONObject();               
//                jsonObj.put("column_list", rset.getString("column_list"));
                jsonArray.add(jsonObj);
            }
        } catch (Exception e) {
            System.out.println("Error:ShowDataModel-showData--- " + e);
        }
        return jsonArray;
    }
    
    
    
    
 public String getMessage() {
        return message;
    }

    public String getMsgBgColor() {
        return msgBgColor;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public void setDb_userName(String db_userName) {
        this.db_userName = db_userName;
    }

    public void setDb_userPasswrod(String db_userPasswrod) {
        this.db_userPasswrod = db_userPasswrod;
    }

   
}



    


