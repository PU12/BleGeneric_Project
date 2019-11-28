/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ble.dataEntry.model;

import com.ble.dataEntry.bean.ModelBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shobha
 */
public class Model {
private Connection connection;
    private String driverClass;
    private String connectionString;
    private String db_username;
    private String db_password;
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "yellow";
    private final String COLOR_ERROR = "red";

    public void setConnection() {
        try {
            Class.forName(driverClass);
           // connection = DriverManager.getConnection(connectionString+"?useUnicode=true&characterEncoding=UTF-8&character_set_results=utf8", db_username, db_password);
            connection = (Connection) DriverManager.getConnection(connectionString, db_username, db_password);
        } catch (Exception e) {
            System.out.println("CommandModel setConnection() Error: " + e);
        }
    }

public int getModelTypeId(String ModelType) {
      String query1="select id "
                   +" from modal_type mt "
                   +" where type=? "
                   +" and mt.active='Y'";

        int modal_type_id = 0;
        try {
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(query1);

            stmt.setString(1, ModelType);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            modal_type_id = rs.getInt("id");
        } catch (Exception e) {
            System.out.println("Error inside getNoOfRows CommandModel" + e);
        }
        System.out.println("No of Rows in Table for search is" + modal_type_id);
        return modal_type_id;
    }

public int insertRecord(ModelBean modelBean) {

    int model_type_id = getModelTypeId(modelBean.getModel_type());

        String query = " insert into model(device_name,device_no,warranty_period,remark,model_type_id,device_address) "
                       +" values(?,?,?,?,?,?) ";
        int rowsAffected = 0;
        try {
            java.sql.PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, modelBean.getDevice_name());
            pstmt.setString(2, modelBean.getDevice_no());
            pstmt.setString(3, modelBean.getWarrenty_period());
            pstmt.setString(4, modelBean.getRemark());
            pstmt.setInt(5,model_type_id );
            pstmt.setString(6, modelBean.getDevice_address());

            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while inserting record...." + e);
        }
        if (rowsAffected > 0) {
            message = "Record saved successfully.";
            msgBgColor = COLOR_OK;
        } else {
            message = "Cannot save the record, some error.";
            msgBgColor = COLOR_ERROR;
        }
        return rowsAffected;

    }

public boolean reviseRecords(ModelBean modelBean){
    boolean status=false;
    String query="";
    int rowsAffected=0;
    int model_type_id=getModelTypeId(modelBean.getModel_type());

      String query1 = " SELECT max(revision_no) revision_no FROM model c WHERE c.id = "+modelBean.getModel_id()+" && active='Y' ORDER BY revision_no DESC";
      String query2 = " UPDATE model SET active=? WHERE id = ? && revision_no = ? ";
      String query3 = " INSERT INTO model (id,device_name,device_no,warranty_period,model_type_id,remark,device_address,revision_no,active) VALUES (?,?,?,?,?,?,?,?,?) ";

      int updateRowsAffected = 0;
      try {
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query1);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
           PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query2);
           pst.setString(1,  "N");
           pst.setInt(2,modelBean.getModel_id());
           pst.setInt(3, rs.getInt("revision_no"));
           updateRowsAffected = pst.executeUpdate();
             if(updateRowsAffected >= 1){
             int rev = rs.getInt("revision_no")+1;
             PreparedStatement psmt = (PreparedStatement) connection.prepareStatement(query3);
             psmt.setInt(1,modelBean.getModel_id());
             psmt.setString(2,modelBean.getDevice_name());
             psmt.setString(3,modelBean.getDevice_no());
             psmt.setString(4,modelBean.getWarrenty_period());
             psmt.setInt(5,model_type_id);

             psmt.setString(6,modelBean.getRemark());
             psmt.setString(7,modelBean.getDevice_address());
             psmt.setInt(8,rev);
             psmt.setString(9,"Y");

             int a = psmt.executeUpdate();
              if(a > 0)
              status=true;
             }
           }
          } catch (Exception e)
             {
              System.out.println("CommandModel reviseRecord() Error: " + e);
             }
      if (status) {
             message = "Record updated successfully......";
            msgBgColor = COLOR_OK;
            System.out.println("Inserted");
        } else {
             message = "Record Not updated Some Error!";
            msgBgColor = COLOR_ERROR;
            System.out.println("not updated");
        }

       return status;
    }
  public int getNoOfRows(String searchDeviceName) {
//      String query1="select count(*) "
//                    +" from modal_type mt "
//                    +" where IF('" + searchModelType + "' = '', type LIKE '%%',type =?) "
//                    +" and mt.active='Y'";
      String query1="select count(*) "
                    +" from model m "
                    +" where IF('" + searchDeviceName + "' = '', device_name LIKE '%%',device_name =?) "
                    +" and m.active='Y'";



        int noOfRows = 0;
        try {
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(query1);

            stmt.setString(1, searchDeviceName);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            noOfRows = rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error inside getNoOfRows CommandModel" + e);
        }
        System.out.println("No of Rows in Table for search is" + noOfRows);
        return noOfRows;
    }

  public List<ModelBean> showData(int lowerLimit, int noOfRowsToDisplay,String searchDeviceName) {
        List<ModelBean> list = new ArrayList<ModelBean>();
         String addQuery = " LIMIT " + lowerLimit + ", " + noOfRowsToDisplay;
          if(lowerLimit == -1)
            addQuery = "";
//       String query2="select id,type,remark "
//                     +" from modal_type m "
//                     +" where IF('" + searchModelType + "' = '', type LIKE '%%',type =?) "
//                     +" and m.active='Y'"
//                     +addQuery;
//
       String query2="select m.id,device_name,device_no,warranty_period,type,m.remark,m.device_address "
                      +" from model m,modal_type mt "
                      +" where IF('" + searchDeviceName + "' = '', device_name LIKE '%%',device_name =?) "
                      +" and m.model_type_id = mt.id "
                      +" and m.active='Y' and mt.active='Y'"
                      + addQuery;
        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(query2);
            pstmt.setString(1, searchDeviceName);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                ModelBean modelBean = new ModelBean();

                modelBean.setModel_id(rset.getInt("id"));
                modelBean.setDevice_name(rset.getString("device_name"));
                modelBean.setDevice_no(rset.getString("device_no"));
                modelBean.setWarrenty_period(rset.getString("warranty_period"));
                modelBean.setModel_type(rset.getString("type"));
                 modelBean.setRemark(rset.getString("remark"));
                 modelBean.setDevice_address(rset.getString("device_address"));
                list.add(modelBean);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }

  public int deleteRecord(int model_type_id) {

      String query = "update model set active='N' where id=" + model_type_id;
        int rowsAffected = 0;
        try {
            rowsAffected = connection.prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        if (rowsAffected > 0) {
            message = "Record deleted successfully......";
            msgBgColor = COLOR_OK;
        } else {
            message = "Error Record cannot be deleted.....";
            msgBgColor = COLOR_ERROR;
        }
        return rowsAffected;
    }

  public List<String> getDeviceName(String q) {
        List<String> list = new ArrayList<String>();
        String query = "select device_name from model where active='Y' group by device_name order by device_name desc";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
//            q = q.trim();
            while (rset.next()) {
                String name = rset.getString("device_name");
//                if (name.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(name);
                    count++;
//                }
            }
            if (count == 0) {
                list.add("No such Manufacturer Name exists.......");
            }
        } catch (Exception e) {
            System.out.println(" ERROR inside CommandModel - " + e);
            message = "Something going wrong";
            //messageBGColor = "red";
        }
        return list;
    }
    public List<String> getModelType(String q) {
        List<String> list = new ArrayList<String>();
        String query = "select type from modal_type where active='Y' group by type order by type desc";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
//            q = q.trim();
            while (rset.next()) {
                String type = rset.getString("type");
//                if (type.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(type);
                    count++;
//                }
            }
            if (count == 0) {
                list.add("No such Manufacturer Name exists.......");
            }
        } catch (Exception e) {
            System.out.println(" ERROR inside CommandModel - " + e);
            message = "Something going wrong";
            //messageBGColor = "red";
        }
        return list;
    }
   public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Error inside closeConnection CommandModel:" + e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getDb_password() {
        return db_password;
    }

    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }

    public String getDb_username() {
        return db_username;
    }

    public void setDb_username(String db_username) {
        this.db_username = db_username;
    }

    public String getDriverClass() {
        return driverClass;
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
