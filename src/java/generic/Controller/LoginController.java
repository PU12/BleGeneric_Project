/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic.Controller;


import generic.Model.LoginModel;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginModel loginModel = new LoginModel();
        ServletContext ctx = getServletContext();
        loginModel.setDriverClass(ctx.getInitParameter("driverClass"));
        loginModel.setConnectionString(ctx.getInitParameter("connectionString"));
        loginModel.setDb_userName(ctx.getInitParameter("db_username"));
        loginModel.setDb_userPassword(ctx.getInitParameter("db_password"));
       loginModel.setConnection();
        String task = request.getParameter("task");

        if (task == null) {
            task = "";
        }
        try{
        if (task.equals("Login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean userAuthentic = loginModel.isUserAuthentic(username, password);
            if (userAuthentic == true) {
                request.getRequestDispatcher("/TableViewCont").forward(request, response);
                int login_id = loginModel.getLoginId(username,password);
                HttpSession session=request.getSession();
              session.setAttribute("login_id",login_id); 
            } else {
                request.setAttribute("message", loginModel.getMessage());
                request.setAttribute("msgBgColor", loginModel.getMsgBgColor());
                request.getRequestDispatcher("/TableViewCont").forward(request, response);
            }
        }
        }

             catch (Exception ex) {
                System.out.println("Exception in insertEnergyMeterData" + ex);
                
            } finally {
                loginModel.closeConnection();
               
            }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}


