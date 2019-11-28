package generic.Model;

import Bean.GenericBean;
import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.io.*;

import java.sql.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class GenericModel extends DBConnection{
    private static Connection connection;

    
    private String driverClass;
    private String connectionString;
    private String db_userName;
    private String db_userPassword;
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "lightyellow";
    private final String COLOR_ERROR = "red";

    
   
   
     public static boolean save(String query, GenericBean cb, int login_id, String query1, String periString,String colString) {

        boolean status = false;
        try {
//       String query1=query;

            PreparedStatement ps;
            ps = connection.prepareStatement(query);
            // ps.setInt(1,c.getId());
//       ps.setString(1,c.getTablename());
//       ps.setString(2,c.getColumn_list());
//      

            int a = ps.executeUpdate();
            if (a > 0) {
                status = true;
            }
            boolean table = table(cb, login_id, query1,periString,colString);
            if (table) {
                status = true;
            } else {
                status = false;
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return status;

    }

    public static boolean table(GenericBean cb, int login_id, String query, String perString, String colString) {

        boolean status = false;
        try {
            String query1 = "insert into table_record(tablename,column_list,login_id,sql_query,peripheral_list,column_details) values(?,?,?,?,?,?)";
            PreparedStatement ps = null;
            ps = connection.prepareStatement(query1);
            // ps.setInt(1,c.getId());
            ps.setString(1, cb.getTablename());
            ps.setString(2, cb.getColumn_list());
            ps.setInt(3, login_id);
            ps.setString(4, query);
            ps.setString(5, perString);
            ps.setString(6, colString);

            int a = ps.executeUpdate();
            if (a > 0) {
                status = true;
            }

        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return status;

    }
 
   
  public void setConnection() {
        try {
            Class.forName(driverClass);
            connection = (Connection)DriverManager.getConnection(connectionString, db_userName, db_userPassword);
        } catch (Exception e) {
            System.out.println("Image setConnection() Error: " + e);
        }
    }
        public void closeConnection() {
        try{
            this.connection.close();
        }catch(Exception ex){
            System.out.println("ERROR: on closeConnection() in ClientResponderModel : " + ex);
        }
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public void setDb_userName(String db_userName) {
        this.db_userName = db_userName;
    }

    public void setDb_userPassword(String db_userPassword) {
        this.db_userPassword = db_userPassword;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgBgColor() {
        return msgBgColor;
    }

    public void setMsgBgColor(String msgBgColor) {
        this.msgBgColor = msgBgColor;
    }
}
