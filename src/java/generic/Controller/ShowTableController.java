/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic.Controller;


import Bean.ShowDataBean;
import generic.Model.ShowTableModel;
import java.sql.Connection;
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
public class ShowTableController extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int lowerLimit, noOfRowsTraversed, noOfRowsToDisplay = 15, noOfRowsInTable;
        ServletContext ctx = getServletContext();

        ShowTableModel showdataModel = new   ShowTableModel();
        showdataModel.setDriverClass(ctx.getInitParameter("driverClass"));
      showdataModel.setConnectionString(ctx.getInitParameter("connectionString"));
        showdataModel.setDb_userName(ctx.getInitParameter("db_username"));
       showdataModel.setDb_userPasswrod(ctx.getInitParameter("db_password"));
    showdataModel.setConnection();
    String tablename=null;
    
    
    String option = request.getParameter("Points");
        String task = request.getParameter("task");
        if (task == null) {
            task = "";
        }
        try{
         if (task.equals("showtable")){
        
           tablename = request.getParameter("tablename");
        showdataModel.showtable(tablename);
        }
        }catch(Exception e){
        System.out.println("exception" +e);
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
        noOfRowsInTable = showdataModel.getNoOfRows();                  // get the number of records (rows) in the table.
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
//        List<ShowDataBean> table_recordList = showdataModel.showData(lowerLimit, noOfRowsToDisplay);
//        lowerLimit = lowerLimit + table_recordList.size();
//        noOfRowsTraversed = table_recordList.size();
//
//        // Now set request scoped attributes, and then forward the request to view.
//        request.setAttribute("lowerLimit", lowerLimit);
//        request.setAttribute("noOfRowsTraversed", noOfRowsTraversed);
//        request.setAttribute("table_recordList",table_recordList);

        if ((lowerLimit - noOfRowsTraversed) == 0) {     // if this is the only data in the table or when viewing the data 1st time.
            request.setAttribute("showFirst", "false");
            request.setAttribute("showPrevious", "false");
        }
        if (lowerLimit == noOfRowsInTable) {             // if No further data (rows) in the table.
            request.setAttribute("showNext", "false");
            request.setAttribute("showLast", "false");
        }
        request.setAttribute("IDGenerator", new xyz());
        request.setAttribute("message", showdataModel.getMessage());
        request.setAttribute("msgBgColor", showdataModel.getMsgBgColor());
        request.getRequestDispatcher("table_list_show").forward(request, response);
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
