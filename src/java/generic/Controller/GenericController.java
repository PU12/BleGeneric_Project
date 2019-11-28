package generic.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Bean.GenericBean;
import generic.Model.GenericModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class GenericController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        ServletContext ctx = getServletContext();
        GenericModel successModel = new GenericModel();
        successModel.setDriverClass(ctx.getInitParameter("driverClass"));
        successModel.setConnectionString(ctx.getInitParameter("connectionString"));
        successModel.setDb_userName(ctx.getInitParameter("db_username"));
        successModel.setDb_userPassword(ctx.getInitParameter("db_password"));
        successModel.setConnection();
        List<String> items = new ArrayList<String>();
        int login_id = 0;
        int check1 = 0;

        HttpSession session = req.getSession();
        if (session != null) {
            login_id = Integer.parseInt(session.getAttribute("login_id").toString());
        }

        GenericBean cb = new GenericBean();
        String task = req.getParameter("task");

        if (task == null) {
            task = "";
        }

        int colm = Integer.parseInt(req.getParameter("colm"));
        int check = colm;
        String tablename = req.getParameter("tablename");
        String query = "create table " + tablename + "(";
        String query1 = "create table IF NOT EXISTS " + tablename + "(";
        String colname = "";

        String pk = "";

        String status = "";

        String AI = "";
        String foreginkey = "";
        String ttype = "";
        String type_varchar="";
        int size = 0;
        String defaultv = "";
        String peripheraltype = "";
        Map<String, String> peripheral_map = new HashMap<>();
        Map<String, String> columnDetail = new HashMap<>();
        if (task.equals("Create Table")) {
            System.out.println(tablename);

            for (int j = 0; j < colm; j++) {
                try {
//            int  table_id = Integer.parseInt(req.getParameter("table_id"+j));

                    colname = req.getParameter("colname" + j);

                    pk = req.getParameter("PK" + j);

                    status = req.getParameter("status" + j);

                    AI = req.getParameter("AI" + j);
                    foreginkey = req.getParameter("morelocations" + j);
                    ttype = req.getParameter("ttype" + j);

                    size = Integer.parseInt(req.getParameter("size" + j));
                    defaultv = req.getParameter("defaultv" + j);
                    peripheraltype = req.getParameter("peripheraltype" + j);

                    if (peripheraltype != "") {
                        peripheral_map.put(peripheraltype, colname);
                    }
                    if (pk == null) {
//                        if(peripheraltype.equals("Images")||peripheraltype.equals("Image")||peripheraltype.equals("Audio")){
//                            query = query + colname + " VARCHAR(" + size + ") ";
//                               if (j !=colm - 1) {
//                                query = query + ",";
//                               }
//                        }else{

                        if (ttype.equals("DOUBLE")) {
                            query = query + colname + " " + ttype + " ";
                            query1 = query1 + colname + " String  ";
                        } else if (ttype.equals("VARCHAR")) {
                            query = query + colname + " VARCHAR(" + size + ") ";
                            query1 = query1 + colname + " STRING  ";
                        } else if (ttype.equals("INT")) {
                            query = query + colname + " " + ttype + "(" + size + ") ";
                            query1 = query1 + colname + " INTEGER  ";
                        }
                        else {
                            query = query + colname + " " + ttype + "(" + size + ") ";

                        }

                        if (status == null) {
                            query = query + "NULL ";
                            if (defaultv != null && defaultv != "") {
                                query = query + "DEFAULT '" + defaultv + "'";
                            }

                            if (j != colm - 1) {
                                query = query + ",";
                                query1 = query1 + ",";
                            }
                        } else if (status.equals("on")) {
                            query = query + "NOT NULL ";

                            if (defaultv != null && defaultv != "") {
                                query = query + "DEFAULT '" + defaultv + "'";
                            }
                            if (j != colm - 1) {
                                query = query + ",";
                                query1 = query1 + ",";
                            }
                        }

                    } else if (pk.equals("on")) {
                        query = query + colname + " " + ttype + "(" + size + ") ";
                        query1 = query1 + colname + " INTEGER PRIMARY KEY  ";
                        if (status.equals("on")) {
                            query = query + "NOT NULL ";
                            if (AI.equals("on")) {
                                query = query + "AUTO_INCREMENT PRIMARY KEY,";
                                query1 = query1 + "AUTOINCREMENT ,";
                            }
                        }
                    }
                      String col ="";
                items.add(colname);
                   // if (!columnDetail.containsKey(ttype)) {
                      //  columnDetail.put(ttype, colname);
                  //  } 
                  //  else
                 //   {
                        if(ttype.equalsIgnoreCase("varchar")){
                         col = columnDetail.get(ttype);
                         if(check1 == 0){
                         col = colname;
                         }else{
                          col = ";" + colname;
                         }
                         type_varchar += col;
                         check1++;
                        }
                        else if(ttype.equalsIgnoreCase("INT")){
                        col = columnDetail.get(ttype);
                        col =   colname;
                       // col += ";latitude;longitude;created_at";
                        columnDetail.put(ttype, col);
                        }
                 //   }
                } catch (Exception e) {
                    System.out.println("error:" + e);
                }

            }
            String colmun_list = "";

            for (String s : items) {
                colmun_list += s + ",";
            }
//            if (!columnDetail.containsKey(ttype)) {
//                        columnDetail.put(ttype, colname);
//                    } 
            
          //  else {
                type_varchar += ";latitude;longitude;created_at";
                        columnDetail.put(ttype, type_varchar);
                
          //  }
            
            colmun_list += "latitude,longitude,created_at";

            System.out.println(colmun_list);
            System.out.println(peripheral_map.toString());
            query = query + ",latitude Double, longitude Double, created_at VARCHAR(100), status VARCHAR(10) NOT NULL DEFAULT 'Y');";
            query1 = query1+",latitude INTEGER,longitude INTEGER,created_at STRING,status STRING NOT NULL DEFAULT 'Y');";
            try {
                System.out.println(query);
                String peripheralString = peripheral_map.toString();
                String columnDetails = columnDetail.toString();
                cb.setTablename(tablename);
                cb.setColumn_list(colmun_list);
                GenericModel.save(query, cb, login_id, query1, peripheralString, columnDetails);
                req.setAttribute("msg", "Table Created");
            } catch (Exception e) {
                System.out.println("error:" + e);
            }

        }
           req.getRequestDispatcher("/generic.jsp").forward(req, res);
           
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
