/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic.Controller;

import Bean.TableViewBean;
import generic.Model.TableViewModel;
import com.ts.util.xyz;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class TableViewController extends HttpServlet {

     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int lowerLimit, noOfRowsTraversed, noOfRowsToDisplay = 7, noOfRowsInTable;
        ServletContext ctx = getServletContext();

        TableViewModel tableviewModel = new  TableViewModel();
        tableviewModel.setDriverClass(ctx.getInitParameter("driverClass"));
       tableviewModel.setConnectionString(ctx.getInitParameter("connectionString"));
        tableviewModel.setDb_userName(ctx.getInitParameter("db_username"));
        tableviewModel.setDb_userPasswrod(ctx.getInitParameter("db_password"));
      tableviewModel.setConnection();
        String task = request.getParameter("task");
        if (task == null) {
            task = "";
        }
    
        try {
            lowerLimit = Integer.parseInt(request.getParameter("lowerLimit"));
            noOfRowsTraversed = Integer.parseInt(request.getParameter("noOfRowsTraversed"));
        } catch (Exception e) {
            lowerLimit = noOfRowsTraversed = 0;
        }
        String buttonAction = request.getParameter("buttonAction"); // Holds the name of any of the four buttons: First, Previous, Next, Delete.
        if (buttonAction == null) {
            buttonAction = "none";
        }
        noOfRowsInTable = tableviewModel.getNoOfRows();                  // get the number of records (rows) in the table.
        if (buttonAction.equals("Next")); // lowerLimit already has value such that it shows forward records, so do nothing here.
        else if (buttonAction.equals("Previous")) {
            int temp = lowerLimit - noOfRowsToDisplay - noOfRowsTraversed;
            if (temp < 0) {
                noOfRowsToDisplay = lowerLimit - noOfRowsTraversed;
                lowerLimit = 0;
            } else {
                lowerLimit = temp;
            }
        } else if (buttonAction.equals("First")) {
            lowerLimit = 0;
        } else if (buttonAction.equals("Last")) {
            lowerLimit = noOfRowsInTable - noOfRowsToDisplay;
            if (lowerLimit < 0) {
                lowerLimit = 0;
            }
        }
       
          // Logic to show data in the table.
        List<TableViewBean> table_recordList = tableviewModel.showData(lowerLimit, noOfRowsToDisplay);
        lowerLimit = lowerLimit + table_recordList.size();
        noOfRowsTraversed = table_recordList.size();

        // Now set request scoped attributes, and then forward the request to view.
        request.setAttribute("lowerLimit", lowerLimit);
        request.setAttribute("noOfRowsTraversed", noOfRowsTraversed);
        request.setAttribute("table_recordList",table_recordList);

        if ((lowerLimit - noOfRowsTraversed) == 0) {     // if this is the only data in the table or when viewing the data 1st time.
            request.setAttribute("showFirst", "false");
            request.setAttribute("showPrevious", "false");
        }
        if (lowerLimit == noOfRowsInTable) {             // if No further data (rows) in the table.
            request.setAttribute("showNext", "false");
            request.setAttribute("showLast", "false");
        }
        request.setAttribute("IDGenerator", new xyz());
        request.setAttribute("message", tableviewModel.getMessage());
        request.setAttribute("msgBgColor", tableviewModel.getMsgBgColor());
        request.getRequestDispatcher("tableview").forward(request, response);
     }
      @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
