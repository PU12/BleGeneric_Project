/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generic.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shruti
 */
public class LoginModel {

    private Connection connection;
    private String driverClass;
    private String connectionString;
    private String db_userName;
    private String db_userPassword;
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "lightyellow";
    private final String COLOR_ERROR = "red";

    public boolean isUserAuthentic(String username, String password) {
        String  username1;
        String  password1;
        boolean isAuthentic = false;
        String query = " SELECT username, password FROM login ";
        PreparedStatement pstmt;
        ResultSet rset;
        Map<String, String> logindetails = new HashMap<String, String>();
        try {
            pstmt = connection.prepareStatement(query);
            rset = pstmt.executeQuery();
            while (rset.next()) {
               username1 = rset.getString("username");
              password1 = rset.getString("password");
                logindetails.put(username1, password1);
            }
          
            if (logindetails.containsKey(username) && logindetails.containsValue(password)) {
                isAuthentic = true;
            } else {
                message = "User does not exists.";
                msgBgColor = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("LoginModel isUserAuthentic error:" + e);
        }
        return isAuthentic;
    }
    
    public int getLoginId(String username, String password) {
        String query = " SELECT login_id FROM login where username = '"+username+"' AND password='"+password+"'";
        PreparedStatement pstmt;
        ResultSet rset;
        int loginId = 0;
        try {
            pstmt = connection.prepareStatement(query);
            rset = pstmt.executeQuery();
            rset.next();
            loginId = rset.getInt("login_id");
        } catch (SQLException e) {
            System.out.println("LoginModel getLoginId error:" + e);
        }
        
        return loginId;
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
