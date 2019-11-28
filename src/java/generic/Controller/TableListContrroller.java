/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic.Controller;

import Bean.GenericBean;
import Bean.TableListBean;
import Bean.PdfBean;
import Bean.TableListBeanPdf;
import generic.Model.TableListModel;
import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;
import com.ts.util.xyz;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Prachi
 */
public class TableListContrroller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int lowerLimit, noOfRowsTraversed, noOfRowsToDisplay = 5, noOfRowsInTable = 0;
        ServletContext ctx = getServletContext();

        TableListModel tablelistModel = new TableListModel();
        tablelistModel.setDriverClass(ctx.getInitParameter("driverClass"));
        tablelistModel.setConnectionString(ctx.getInitParameter("connectionString"));
        tablelistModel.setDb_userName(ctx.getInitParameter("db_username"));
        tablelistModel.setDb_userPasswrod(ctx.getInitParameter("db_password"));
        tablelistModel.setConnection();
        List<List<String>> addresses = new ArrayList<List<String>>();
        // Logic to show  dropdown list of table
        String imageid;
        int imageid1 = 0;
        String task = request.getParameter("task");
        String task1 = request.getParameter("task1");
        String option = request.getParameter("Points");
        String arr[] = null;
        String arr1[] = null;
        String peripheral = "";
        String image = "";
        String audio = "";
        JSONObject jsonObject = null;
        String buttonAction = request.getParameter("buttonAction");
        String table_name1 = request.getParameter("table_name");
        int length = 0, length1 = 0;

        if (task == null) {
            task = "";
        }
        try {
            List<TableListBean> table_List = tablelistModel.showData();// getting collumn list to show in drop down list
            request.setAttribute("table_List", table_List);
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }

        //lower limit for pagination
        try {
            lowerLimit = Integer.parseInt(request.getParameter("lowerLimit"));
            noOfRowsTraversed = Integer.parseInt(request.getParameter("noOfRowsTraversed"));
        } catch (Exception e) {
            lowerLimit = noOfRowsTraversed = 0;
        }

        //noOfRowsInTable = tablelistModel.getNoOfRows();                  // get the number of records (rows) in the table.
        if (option != null) {
            noOfRowsInTable = tablelistModel.getNoOfRowsOfDynamicTable(option);
        }

        if (buttonAction != null) {
            noOfRowsInTable = tablelistModel.getNoOfRowsOfDynamicTable(table_name1);
        }

        // Holds the name of any of the four buttons: First, Previous, Next, Delete.
        if (buttonAction == null) {
            buttonAction = "none";
        }
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

        // Get peripheral List which contain column name which is image and audio and vedio
        try {
            //option is table table that is selected
            JSONArray tableData = new JSONArray();
            // int length = 0, length1 = 0;
            List<String> table_column_List = new ArrayList<>();

            if (option == null) {

                //option = "";
                if (!task.equalsIgnoreCase("Liesten_Audio")) {
                    if (!task.equalsIgnoreCase("View_Image")) {
                        if (table_name1 != null) {
                            tableData = tablelistModel.getTableData(table_name1, lowerLimit, noOfRowsToDisplay);
                            table_column_List = tablelistModel.getColumnList(table_name1);
                            length = table_column_List.size();
                            length1 = table_column_List.size() - 1;
                            request.setAttribute("table_column_List", table_column_List);
                            request.setAttribute("table_name", table_name1);
                        }
                    } else {
                        //String table_name1 = request.getParameter("table");
                        lowerLimit = 0;
                        noOfRowsTraversed = Integer.parseInt(request.getParameter("noOfRowsTraversed"));
                        option = table_name1;
//                    lowerLimit = lowerLimit - noOfRowsToDisplay;
                        tableData = tablelistModel.getTableData(option, 0, noOfRowsToDisplay);
                        table_column_List = tablelistModel.getColumnList(option);
                        length = table_column_List.size();
                        length1 = table_column_List.size() - 1;
                        request.setAttribute("table_column_List", table_column_List);
                        request.setAttribute("table_name", option);
                    }
                }
                if (task.equalsIgnoreCase("Liesten_Audio")) {
                    if (table_name1 != null) {
                        lowerLimit = 0;
                        tableData = tablelistModel.getTableData(table_name1, 0, noOfRowsToDisplay);
                        table_column_List = tablelistModel.getColumnList(table_name1);
                        length = table_column_List.size();
                        length1 = table_column_List.size() - 1;
                        request.setAttribute("table_column_List", table_column_List);
                        request.setAttribute("table_name", table_name1);
                    } else {
                        lowerLimit = 0;
                        String table_name2 = request.getParameter("table");
                        noOfRowsTraversed = Integer.parseInt(request.getParameter("noOfRowsTraversed"));
                        //lowerLimit = Integer.parseInt(request.getParameter("lowerLimit"));
                        option = table_name2;
                        // lowerLimit = lowerLimit - noOfRowsToDisplay;
                        tableData = tablelistModel.getTableData(option, 0, noOfRowsToDisplay);
                        table_column_List = tablelistModel.getColumnList(option);
                        length = table_column_List.size();
                        length1 = table_column_List.size() - 1;
                        request.setAttribute("table_column_List", table_column_List);
                        request.setAttribute("table_name", option);
                    }
                }

            } else if (option != null) {

                tableData = tablelistModel.getTableData(option, lowerLimit, noOfRowsToDisplay);
                table_column_List = tablelistModel.getColumnList(option);
                length = table_column_List.size();
                length1 = table_column_List.size() - 1;
                request.setAttribute("table_column_List", table_column_List);
                request.setAttribute("table_name", option);
            }
            if (option != null) {
                peripheral = tablelistModel.getperipheral_list(option);
                String value = peripheral;
                value = value.substring(1, value.length() - 1);           //remove curly brackets
                String peripherals[] = value.split(",");              //split the string to creat key-value pairs
                Map<String, String> map = new HashMap<>();

                for (String pair : peripherals) //iterate over the pairs
                {
                    String[] entry = pair.split("=");                   //split the pairs to get key and value 
                    map.put(entry[0].trim(), entry[1].trim());          //add them to the hashmap and trim whitespaces
                }
                int length2 = peripherals.length;
                for (int i = 0; i < length2; i++) {
                    String check = peripherals[i].split("=")[0].trim();
                    if (check.equalsIgnoreCase("Audio")) {
                        audio = (peripherals[i].split("=")[0].trim().equals("Audio") ? peripherals[i].split("=")[1] : "");
                    } else {
                        image = (peripherals[i].split("=")[0].equals("Image") || peripherals[i].split("=")[0].equals("Images") ? peripherals[i].split("=")[1] : "").trim();
                    }

                }
//                if (map.containsKey("Audio") && map.containsKey("Images") || map.containsKey("Image")) {
//                    image = peripherals[0].split("=")[0].equals("Image") || peripherals[0].split("=")[0].equals("Images") ? peripherals[0].split("=")[1] : "";
//                    audio = peripherals[1].split("=")[0].equals("Audio") ? peripherals[1].split("=")[1] : "";
//                } else if (map.containsKey("Images") || map.containsKey("Image")) {
//
//                    image = peripherals[0].split("=")[0].equals("Image") || peripherals[0].split("=")[0].equals("Images") ? peripherals[0].split("=")[1] : "";
//
//                } else if (map.containsKey("Audio")) {
//                    audio = peripherals[1].split("=")[0].equals("Audio") ? peripherals[1].split("=")[1] : "";
//                }

            } else {
                peripheral = tablelistModel.getperipheral_list(table_name1);
                String peripherals[] = peripheral.substring(1, peripheral.length() - 1).split(", ");
                int length2 = peripherals.length;
                for (int i = 0; i < length2; i++) {
                    String check = peripherals[i].split("=")[0].trim();
                    if (check.equalsIgnoreCase("Audio")) {
                        audio = (peripherals[i].split("=")[0].trim().equals("Audio") ? peripherals[i].split("=")[1] : "");
                    } else {
                        image = (peripherals[i].split("=")[0].trim().equals("Image") || peripherals[i].split("=")[0].equals("Images") ? peripherals[i].split("=")[1] : "");
                    }

                }

//                if (length2 < 2) {
//                    String check = peripherals[0].split("=")[0];
//                    if (check.equalsIgnoreCase("Audio")) {
//                        audio = peripherals[0].split("=")[0].equals("Audio") ? peripherals[0].split("=")[1] : "";
//                    } else {
//                        image = peripherals[0].split("=")[0].equals("Image") || peripherals[0].split("=")[0].equals("Images") ? peripherals[0].split("=")[1] : "";
//                    }
//                } else {
//                    for (int i = 0; i < length2; i++) {
//                        String check = peripherals[i].split("=")[0];
//                        if (check.equalsIgnoreCase("Audio")) {
//                            audio = peripherals[i].split("=")[0].equals("Audio") ? peripherals[i].split("=")[1] : "";
//                        } else {
//                            image = peripherals[i].split("=")[0].equals("Image") || peripherals[i].split("=")[0].equals("Images") ? peripherals[i].split("=")[1] : "";
//                        }
//
//                    }
//                    //  image = peripherals[0].split("=")[0].equals("Image") || peripherals[0].split("=")[0].equals("Images") ? peripherals[0].split("=")[1] : "";
//                    //audio = peripherals[1].split("=")[0].equals("Audio") ? peripherals[1].split("=")[1] : "";
//                }
            }

            JSONArray jsonArray = new JSONArray(tableData.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                ArrayList<String> singleAddress = new ArrayList<String>();
                //singleAddress.clear();
                jsonObject = jsonArray.getJSONObject(i);
                Iterator str = table_column_List.iterator();
                String check2;
                for (int j = 0; j < length; j++) {

                    if (str.hasNext()) {
                        String check1 = table_column_List.get(j);
                        if ((check1.equals("id"))) {

                            check2 = Integer.toString(jsonObject.getInt(check1));
                            singleAddress.add(check2);

                        } else if (!(check1.equals("id"))) {
                            if ((check1.equals(image))) {
                                check2 = "test";
                                singleAddress.add(check2);
                            } else if (check1.equals(audio)) {
                                check2 = "test1";
                                singleAddress.add(check2);
                            } else {
                                check2 = jsonObject.getString(check1);
                                singleAddress.add(check2);
                            }
                        }
                    }
                }

                addresses.add(singleAddress);
            }
            request.setAttribute("Table_List_length", length1);
            request.setAttribute("table_values_List", addresses);

//                  request.setAttribute("imageid", imageid);
            /////show table logic
            lowerLimit = lowerLimit + addresses.size();
            noOfRowsTraversed = addresses.size();

            // Now set request scoped attributes, and then forward the request to view.
            request.setAttribute("lowerLimit", lowerLimit);
            request.setAttribute("noOfRowsTraversed", noOfRowsTraversed);

            if ((lowerLimit - noOfRowsTraversed) == 0) {     // if this is the only data in the table or when viewing the data 1st time.
                request.setAttribute("showFirst", "false");
                request.setAttribute("showPrevious", "false");
            }
            if (lowerLimit == noOfRowsInTable) {             // if No further data (rows) in the table.
                request.setAttribute("showNext", "false");
                request.setAttribute("showLast", "false");
            }
            request.setAttribute("IDGenerator", new xyz());
            request.setAttribute("message", tablelistModel.getMessage());
            request.setAttribute("msgBgColor", tablelistModel.getMsgBgColor());

        } catch (Exception e) {
            System.out.println("Exception" + e);

        }
        try {
            if (table_name1 == null) {
                if (task.equals("View_Image")) {

                    imageid1 = Integer.parseInt(request.getParameter("imageid"));
                    String table_name = (request.getParameter("table"));
//                    peripheral = tablelistModel.getperipheral_list(table_name);
//                    String peripherals[] = peripheral.substring(1, peripheral.length() - 1).split(", ");
//                    image = peripherals[0].split("=")[0].equals("Image") || peripherals[0].split("=")[0].equals("Images") ? peripherals[0].split("=")[1] : "";

                    peripheral = tablelistModel.getperipheral_list(table_name);
                    String peripherals[] = peripheral.substring(1, peripheral.length() - 1).split(", ");
                    int length2 = peripherals.length;
                    for (int i = 0; i < length2; i++) {
                        String check = peripherals[i].split("=")[0];
                        if (check.equalsIgnoreCase("Audio")) {
                            audio = (peripherals[i].split("=")[0].trim().equals("Audio") ? peripherals[i].split("=")[1] : "");
                        } else {
                            image = (peripherals[i].split("=")[0].trim().equals("Image") || peripherals[i].split("=")[0].equals("Images") ? peripherals[i].split("=")[1] : "");
                        }

                    }

                    List imageList = new ArrayList();
                    String destinationPath = "";
//                       int general_image_details_id=Integer.parseInt(request.getParameter("general_image_details_id"));                       
                    String destination = tablelistModel.getImageDestinationPath(table_name, image, imageid1);
//                         String task2=request.getParameter("task1");
//                         destination = destination +"/"+task2;

                    File f = new File(destination);
                    File folder = new File(destination);
                    File[] listOfFiles = folder.listFiles();

                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
//                      
                            String name1 = listOfFiles[i].getName();
                            /////////////////////////////////////filterImageName//////////////////////////////////////////////                                                       
                            GenericBean fd = new GenericBean();
                            fd.setImage_name(name1);
//                                   fd.setTask(task2);
                            imageList.add(fd);

                        }
                    }
                    int image_list_length = imageList.size();
                    request.setAttribute("imageList", imageList);
                    request.setAttribute("table_name", table_name);
                    request.setAttribute("imageid1", imageid1);
                    request.setAttribute("image_list_lenght", image_list_length);
                    request.getRequestDispatcher("multipleImage").forward(request, response);
                    return;
                }
            } else if (task1.equals("getImage") || task1.equals("getImageThumb")) {

                //int traffic_police_id = Integer.parseInt(request.getParameter("traffic_police_id").trim());
                String table_name = request.getParameter("table_name");
                peripheral = tablelistModel.getperipheral_list(table_name);
                String peripherals[] = peripheral.substring(1, peripheral.length() - 1).split(", ");
                int length2 = peripherals.length;
                for (int i = 0; i < length2; i++) {
                    String check = peripherals[i].split("=")[0];
                    if (check.equalsIgnoreCase("Audio")) {
                        audio = (peripherals[i].split("=")[0].trim().equals("Audio") ? peripherals[i].split("=")[1] : "");
                    } else {
                        image = (peripherals[i].split("=")[0].trim().equals("Image") || peripherals[i].split("=")[0].equals("Images") ? peripherals[i].split("=")[1] : "");
                    }

                }

//                //String image_name2 = image_name1[0];
                imageid1 = Integer.parseInt(request.getParameter("imageid1").trim());
                String image_name1 = request.getParameter("image_name").trim();
                String destinationPath = tablelistModel.getImageDestinationPath(table_name, image, imageid1);
                destinationPath = destinationPath + "\\" + image_name1;
                File f = new File(destinationPath);
                FileInputStream fis = null;
                if (!f.exists()) {
                    destinationPath = "C:\\ssadvt_repository\\prepaid\\general\\no_image.png";
                    f = new File(destinationPath);
                }
                fis = new FileInputStream(f);
                if (destinationPath.contains("pdf")) {
                    response.setContentType("pdf");
                } else {
                    response.setContentType("image/jpeg");
                }

                //  response.addHeader("Content-Disposition", "attachment; filename=\"" + f.getName() + "\"");
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
                // BufferedImage bi=ImageIO.read(f);
                response.setContentLength(fis.available());
                ServletOutputStream os = response.getOutputStream();
                BufferedOutputStream out = new BufferedOutputStream(os);
                int ch = 0;
                while ((ch = bis.read()) != -1) {
                    out.write(ch);
                }

                bis.close();
                fis.close();
                out.close();
                os.close();
                response.flushBuffer();

                return;

            }
        } catch (Exception e) {
            System.out.println("exception in image view part" + e);
        }
        //////////////////audio part
        int audioid1 = 0;

        try {
            if (table_name1 == null) {

                if (task.equals("Liesten_Audio")) {

                    audioid1 = Integer.parseInt(request.getParameter("audioid"));
                    String table_name = (request.getParameter("table"));
                    peripheral = tablelistModel.getperipheral_list(table_name);
                    String peripherals[] = peripheral.substring(1, peripheral.length() - 1).split(", ");
                    int length2 = peripherals.length;
                    for (int i = 0; i < length2; i++) {
                        String check = peripherals[i].split("=")[0];
                        if (check.equalsIgnoreCase("Audio")) {
                            audio = (peripherals[i].split("=")[0].trim().equals("Audio") ? peripherals[i].split("=")[1] : "");
                        } else {
                            image = (peripherals[i].split("=")[0].trim().equals("Image") || peripherals[i].split("=")[0].equals("Images") ? peripherals[i].split("=")[1] : "");
                        }

                    }
                    List audioList = new ArrayList();
                    String destinationPath = "";
//                       int general_image_details_id=Integer.parseInt(request.getParameter("general_image_details_id"));                       
                    String destination = tablelistModel.getAudioDestinationPath(table_name, audio, audioid1);
//                         String task2=request.getParameter("task1");
//                         destination = destination +"/"+task2;

                    File f = new File(destination);
                    File[] listOfFiles = f.listFiles();
                    String name1 = listOfFiles[0].getName();
                    String destination_path = destination + "\\" + name1;
                    response.setContentType("audio/x-wav");
                    FileInputStream fis = new FileInputStream(destination_path);
                    response.addHeader("Content-Disposition", "attachment; filename=\"audio.mp3\"");
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    response.setContentLength(fis.available());
                    ServletOutputStream os = response.getOutputStream();
                    BufferedOutputStream out = new BufferedOutputStream(os);
                    int ch = 0;
                    while ((ch = bis.read()) != -1) {
                        out.write(ch);
                    }
                    bis.close();
                    fis.close();
                    out.close();
                    os.close();
                    response.flushBuffer();
                    return;
                }
            }
        } catch (Exception e) {

            System.out.println("exception in audio part" + e);
        } /////////////pdf part

        try {
            if (task.equals("generate_pdf")) {
                String jrxmlFilePath;
                List list = null;
                int pdf_id = Integer.parseInt(request.getParameter("pdfid"));
                String table_name3 = (request.getParameter("table"));
//                 List<TableListBean> data_pdf =  tablelistModel.showDataPdf(table_name3,pdf_id);    
//            List<String>  column_pdf = tablelistModel.getColumnList(table_name3);
//           JSONArray value_pdf = tablelistModel.getValueList(table_name3,pdf_id);
//             int length3 = column_pdf.size();
//             
//                  for (int i = 0; i < length3; i++) 
//                  {
//                  
//                  }

//              
//                    TableListBean tableListBean = new TableListBean();
//               
//                    List<String> imagePathList = tablelistModel.getImagePath(pdf_id); 
//
//                    int listSize = imagePathList.size();
//                    for(int i=0;i<listSize;i++){
//                        if(i ==0){
//                            tableListBean.setImgPath(imagePathList.get(i));
//                        }
//                        if(i ==1){
//                            tableListBean.setImgPath1(imagePathList.get(i));
//                        }
//                    }
//            
                response.setContentType("application/pdf");
                ServletOutputStream servletOutputStream = response.getOutputStream();

                //jrxmlFilePath = ctx.getRealPath("/report/shift/onlineChallanReport3.jrxml");
                //jrxmlFilePath = ctx.getRealPath("/report/TableDataReport.jrxml");
                jrxmlFilePath = ctx.getRealPath("/report/TestReport.jrxml");

//                List<TableListBeanPdf> data_pdf = tablelistModel.showDataPdf(table_name3, pdf_id);
                List<TableListBeanPdf> data_pdf = tablelistModel.showDataPdf(table_name3, pdf_id);
                // list = tubeWellSurveyModel.showAllData(searchOfficerName,searchMobile,searchPlaceOf,searchAmount ,searchcaseDate,searchBookNo, searchOffenceType, searchActType, searchVehicleType, searchVehicleNo, searchFromDate, searchToDate, searchJarayamNo, searchOffenceCode, searchOffenderLicenseNo, searchReceiptBookNo,searchChallanNo);               // list.add(userEntryByImageBean1);
                byte[] reportInbytes = tablelistModel.generateRecordList(jrxmlFilePath, data_pdf);
                response.setContentLength(reportInbytes.length);
                servletOutputStream.write(reportInbytes, 0, reportInbytes.length);
                servletOutputStream.flush();
                servletOutputStream.close();
                tablelistModel.closeConnection();
                return;

            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        try {
            if (task.equals("GetCordinates1")) {
                int map_id = Integer.parseInt(request.getParameter("mapid"));
                String table_name3 = (request.getParameter("table"));
                String latlong = tablelistModel.latlong(map_id, table_name3);
                String latlong1[] = latlong.split(",");
                String latti1 = latlong1[0];
                String longi1 = latlong1[1];

                if (longi1 == null || longi1.equals("undefined")) {
                    longi1 = "0";
                }
                if (latti1 == null || latti1.equals("undefined")) {
                    latti1 = "0";
                }

                request.setAttribute("longi", longi1);
                request.setAttribute("latti", latti1);
                System.out.println(latti1 + "," + longi1);
                request.getRequestDispatcher("getCordinate").forward(request, response);
                return;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        request.setAttribute("Table_List_length", length1);
        request.setAttribute("table_values_List", addresses);
        // request.setAttribute("IDGenerator", new xyz());
        // request.setAttribute("message", tablelistModel.getMessage());
        //  request.setAttribute("msgBgColor", tablelistModel.getMsgBgColor());
        request.getRequestDispatcher("table_list_show.jsp").forward(request, response);
        tablelistModel.closeConnection();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
