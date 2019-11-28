/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic.webServiceModel;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author prachi
 */
public class DataSendWebServiceModel {

    private Connection connection;
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "yellow";
    private final String COLOR_ERROR = "red";

    public JSONArray getTableRecords() {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = "select * from table_record";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
//                 obj.put("table_record_id",rset.getInt("table_record_id"));
//                 obj.put("tablename",rset.getString("tablename"));
//                 obj.put("column_list",rset.getString("column_list")); 
//                 obj.put("peripheral_list",rset.getString("peripheral_list"));  
                obj.put("sql_query", rset.getString("sql_query"));

                rowData.put(obj);
            }
        } catch (Exception e) {
            System.out.println("Error inside show data of survey: " + e);
        }
        return rowData;
    }

    public String getDestinationPath(String image_uploaded_for) {
        String destination_path = "";
        String query = " SELECT image_destination FROM destination_path ";

        try {
            ResultSet rs = connection.prepareStatement(query).executeQuery();
            if (rs.next()) {
                destination_path = rs.getString("image_destination");
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
        return destination_path;
    }

    public boolean makeDirectory(String dirPathName) {
        boolean result = false;
        File directory = new File(dirPathName);
        if (!directory.exists()) {
            result = directory.mkdirs();
        }
        return result;
    }

    public int insertImageRecord(String image_name) {

        int rowsAffected = 0;
        int general_image_detail_id = 0;
        String imageQuery = "INSERT INTO general_image_details (image_name, image_destination_id) "
                + " VALUES(?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(imageQuery);
            pstmt.setString(1, image_name);
            pstmt.setInt(2, 1);

            rowsAffected = pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                general_image_detail_id = rs.getInt(1);
            }
            pstmt.close();
        } catch (Exception e) {
            System.out.println("Error:keypersonModel--insertRecord-- " + e);
        }
        return general_image_detail_id;
    }

    public void insertRecord(JSONObject json1) {
        String arr1 = "";
        String check1 = null;
        String check2 = null;
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer1 = new StringBuffer();
        String arr2 = "";
        String tableName = json1.get("TableName").toString();
        String columnList[] = getColumnList(tableName).split(",");
        int size = columnList.length;
        int n = size - 1;
        int i = 0;
        try {
            json1.remove("currentTime");
            json1.remove("currentDate");
            json1.remove("TableName");
            Set<String> keyset = json1.keySet();
            for (i = 0; i < size  ; i++) {
                arr1 = columnList[i];
                if (!(arr1.equals("id"))) {                    
                    if (keyset.contains(arr1)) {
                        arr2 = json1.get(arr1).toString();
                        buffer.append(arr1);
                        buffer1.append("'").append(arr2);
                        
                        if (i < keyset.size()) {
                            buffer.append(",");
                            buffer1.append("',");
                        } else {
                            buffer1.append("'");
                        }
                    }

                }
            }
            
            System.out.println("buffer ..." + buffer);
            System.out.println("buffer1 ..." + buffer1);
        } catch (Exception e) {
            System.out.println("Exception is " + e);
        }
        String query = "insert into  " + tableName + "(" + buffer +")" + " VALUES(" + buffer1 + ")";
        try {//Data Retrived : {"longitude":"79.9299386","latitude":"23.1703026","type":"GSM","phoneno":"","deviceid":"911501504294500"}
            PreparedStatement psmt = connection.prepareStatement(query);

            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("DataSendModel Error: " + e);
        }

    }

    public String getColumnList(String tableName) {

        String query = null;
        String columnList = null;
        query = "select column_list from table_record where tablename ='" + tableName + "';";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            columnList = rset.getString("column_list");
        } catch (Exception e) {
            System.out.println("Error inside show data of survey: " + e);
        }
        return columnList;
    }
    
    public String getPeripheralList(String tableName) {

        String query = null;
        String columnList = null;
        query = "select peripheral_list from table_record where tablename ='" + tableName + "';";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            columnList = rset.getString("peripheral_list");
        } catch (Exception e) {
            System.out.println("Error inside show data of survey: " + e);
        }
        return columnList;
    }
  public int getNoOfRows(String tableName) {
        int noOfRows = 0;
        try {
            ResultSet rset = connection.prepareStatement("select count(*) from "+tableName+" ").executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
        } catch (Exception e) {
            System.out.println("TableViewModel getNoOfRows() Error: " + e);
        }
        return noOfRows;
    }
    public Connection getConnection() {
        return connection;
    }

    public void setConnection() {
        try {
            System.out.println("hii inside setConnection() method");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "jpss");
        } catch (Exception e) {
            System.out.println("DataWebServicesModel setConnection() Error: " + e);
        }
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
