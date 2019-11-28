/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic.Model;

import Bean.ColnamePdf;
import Bean.PdfBean;
import Bean.TableListBean;
import Bean.TableListBeanPdf;
import Bean.TableMapBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.json.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author DELL
 */
public class TableListModel {

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

    public int getNoOfRowsOfDynamicTable(String tablename) {
        int noOfRows = 0;
        try {
            ResultSet rset = connection.prepareStatement("select count(*) from " + tablename).executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
        } catch (Exception e) {
            System.out.println("TableViewModel getNoOfRows() Error: " + e);
        }
        return noOfRows;
    }

    public List<TableListBean> showData() {
        List<TableListBean> list = new ArrayList<TableListBean>();

        String query = "SELECT tablename,table_record_id from table_record";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                TableListBean tableview = new TableListBean();
                tableview.setTable_record_id(rset.getInt("table_record_id"));
                tableview.setTablename(rset.getString("tablename"));

                list.add(tableview);
            }
        } catch (Exception e) {
            System.out.println("TableListModel showData() Error: " + e);
        }
        return list;
    }

    public String latlong(int mapid, String tablename) {
        String latlong = null;

        String query = " select longitude,latitude from " + tablename + " where id=" + mapid + " ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            rset.next();

            String latitude = rset.getString("latitude");
            String longitude = rset.getString("longitude");
            latlong = "" + latitude + "," + longitude + "";

        } catch (Exception e) {
            System.out.println("TableListModel showData() Error: " + e);
        }
        return latlong;
    }

    public List<String> getColumnList(String tablename) {
        List<String> list = new ArrayList<>();
        String query = " select column_list from table_record where tablename='" + tablename + "' ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                String column_list = rset.getString("column_list");

                String column = column_list.substring(0, column_list.length() - 1);
                String column_list1[] = column_list.split(",");
                for (int i = 0; i < column_list1.length; i++) {
                    list.add(column_list1[i]);
                }
            }
        } catch (SQLException e) {
            System.out.println("TableListModel getColumnList error:" + e);
        }
        return list;
    }
//        public List<PdfBean> getColumnList1(String tablename) {
//        List<PdfBean> list = new ArrayList<>();
//        
//        String query = " select column_list from table_record where tablename='" + tablename + "' ";
//        try {
//            ResultSet rset = connection.prepareStatement(query).executeQuery();
//            while (rset.next()) {
//                String column_list = rset.getString("column_list");
//
//                String column = column_list.substring(0, column_list.length() - 1);
//                String column_list1[] = column_list.split(",");
//                for (int i = 0; i < column_list1.length; i++) {
////                    list.add(column_list1[i]);
//             PdfBean bean =new  PdfBean();
//                     bean.setColumnname(column_list1[i]);
//                     list.add(bean);
//                  
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("TableListModel getColumnList error:" + e);
//        }
//        return list;
//    }

    public String getperipheral_list(String tablename) {
        String peripheral_list = null;
        String query = " select peripheral_list from table_record where tablename='" + tablename + "' ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            rset.next();
            peripheral_list = rset.getString("peripheral_list");

        } catch (SQLException e) {
            System.out.println("TableListModel getperipheral_list error:" + e);
        }
        return peripheral_list;
    }

    public JSONArray getTableData(String tablename, int lowerLimit, int noOfRowsToDisplay) {
        List<String> list = new ArrayList<String>();
        List<String> list1 = new ArrayList<String>();
        JSONArray columnList = new JSONArray();
        int j = 0, k = 0;
        String query = " select column_list,column_details from table_record where tablename='" + tablename + "' ";

        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                String column_list = rset.getString("column_list");
                String column_details = rset.getString("column_details");
                // String column = column_list.substring(0, column_list.length() - 1);
                String column = column_list.substring(0, column_list.length());
                String detailsSplit = column_details.substring(1, column_details.length() - 1);
                Map<String, String> mapJs = new HashMap<>();
                String query1 = " select " + column + " from " + tablename + " "
                        + " LIMIT " + lowerLimit + ", " + noOfRowsToDisplay;
                try {
                    ResultSet rset1 = connection.prepareStatement(query1).executeQuery();

                    String column_list1[] = column_list.split(",");
                    for (int i = 0; i < column_list1.length; i++) {
                        list.add(column_list1[i]);
                    }
                    String detailSplit1[] = detailsSplit.split(",");
                    for (String pair : detailSplit1) {
                        String[] entry = pair.split("=");
                        //split the pairs to get key and value 
                        for (String m : entry[1].split(";")) {
                            mapJs.put(m.trim(), entry[0].trim());
                        }

                    }
                    while (rset1.next()) {
                        JSONObject jsono = new JSONObject();
                        for (int i = 0; i < column_list1.length; i++) {
                            if (mapJs.containsKey(column_list1[i])) {
                                String type = mapJs.get(column_list1[i]);

                                if (type.equals("VARCHAR")) {
                                    jsono.put(column_list1[i], rset1.getString(column_list1[i]));
                                } else if (type.equals("INT")) {
                                    jsono.put(column_list1[i], rset1.getInt(column_list1[i]));
                                } else if (type.equals("DOUBLE")) {
                                    jsono.put(column_list1[i], rset1.getDouble(column_list1[i]));
                                }
                            }

                        }
                        columnList.put(j, jsono);
                        j++;
                    }
                    System.out.println(columnList);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            System.out.println("Error:column_list Model-" + e);
        }
        return columnList;
    }

    public String getImageDestinationPath(String table_name, String image, int imageid1) {
        String destination_path = "";

        String query = " SELECT " + image + " FROM " + table_name + " where id=" + imageid1 + " ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            if (rset.next()) {
                destination_path = rset.getString(image);

            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
        return destination_path;
    }

    public String getAudioDestinationPath(String table_name, String audio, int audioid1) {
        String destination_path = "";

//        String query = " SELECT " + audio + " , " +audioid1+ "  FROM " + table_name + " ";
        String query = " SELECT " + audio + " FROM " + table_name + " where id=" + audioid1 + " ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            if (rset.next()) {
                destination_path = rset.getString(audio);

            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
        return destination_path;
    }

///part
    public JSONArray getValueList(String table_name3, int pdf_id) {
        List<String> list = new ArrayList<String>();
        List<String> list1 = new ArrayList<String>();
        JSONArray columnValueList = new JSONArray();
        int j = 0, k = 0;
        String query = " select column_list,column_details from table_record where tablename='" + table_name3 + "' ";

        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                String column_list = rset.getString("column_list");
                String column_details = rset.getString("column_details");
                // String column = column_list.substring(0, column_list.length() - 1);
                String column = column_list.substring(0, column_list.length());
                String detailsSplit = column_details.substring(1, column_details.length() - 1);
                Map<String, String> mapJs = new HashMap<>();
                String query1 = " select " + column + " from " + table_name3 + " where id=" + pdf_id;

                try {
                    ResultSet rset1 = connection.prepareStatement(query1).executeQuery();

                    String column_list1[] = column_list.split(",");
                    for (int i = 0; i < column_list1.length; i++) {
                        list.add(column_list1[i]);
                    }
                    String detailSplit1[] = detailsSplit.split(",");
                    for (String pair : detailSplit1) {
                        String[] entry = pair.split("=");
                        //split the pairs to get key and value 
                        for (String m : entry[1].split(";")) {
                            mapJs.put(m.trim(), entry[0].trim());
                        }

                    }
                    while (rset1.next()) {
                        JSONObject jsono = new JSONObject();
                        for (int i = 0; i < column_list1.length; i++) {
                            if (mapJs.containsKey(column_list1[i])) {
                                String type = mapJs.get(column_list1[i]);

                                if (type.equals("VARCHAR")) {
                                    jsono.put(column_list1[i], rset1.getString(column_list1[i]));
                                } else if (type.equals("INT")) {
                                    jsono.put(column_list1[i], rset1.getInt(column_list1[i]));
                                } else if (type.equals("DOUBLE")) {
                                    jsono.put(column_list1[i], rset1.getDouble(column_list1[i]));
                                }
                            }

                        }
                        columnValueList.put(j, jsono);
                        j++;
                    }
                    System.out.println(columnValueList);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            System.out.println("Error:column_list Model-" + e);
        }
        return columnValueList;
    }

    public List<TableListBeanPdf> showDataPdf(String table_name3, int pdf_id) {
        List<ColnamePdf> list = new ArrayList<ColnamePdf>();
        List<TableListBeanPdf> list1 = new ArrayList<TableListBeanPdf>();

        JSONArray value_pdf_data = new JSONArray();
        org.json.JSONObject jsonObject = null;
        String image = "";
        String audio = "";
        int length = 0;
        List<String> value_pdf = new ArrayList<String>();

//        List<PdfBean> column_pdf = getColumnList1(table_name3);
        List<String> column_pdf = getColumnList(table_name3);

        length = column_pdf.size();
    
        value_pdf_data = getValueList(table_name3, pdf_id);
        String peripheral = getperipheral_list(table_name3);
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

        JSONArray jsonArray = new JSONArray(value_pdf_data.toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            String check1 = null;
            String check2 = null;
            jsonObject = jsonArray.getJSONObject(i);
            Iterator str = column_pdf.iterator();

            for (int j = 0; j < length; j++) {

                if (str.hasNext()) {
                    check1 = column_pdf.get(j);
                    if ((check1.equals("id"))) {

                        check2 = Integer.toString(jsonObject.getInt(check1));
                        value_pdf.add(check2);

                    } else if (!(check1.equals("id"))) {
                        if ((check1.equals(image))) {
                            check2 = "test";
                            value_pdf.add(check2);
                        } else if (check1.equals(audio)) {
                            check2 = "test1";
                            value_pdf.add(check2);
                        } else {
                            check2 = jsonObject.getString(check1);
                            value_pdf.add(check2);

                        }
                    }
                }
            }
        }

//        ColnamePdf bean = new ColnamePdf();
//        bean.setColumn_pdf(column_pdf);
//        bean.setValue_pdf(value_pdf);
//        list.add(bean);
        TableListBeanPdf bean1 = new TableListBeanPdf();
//        bean1.setData_pdf(list);
        bean1.setPdf_tablename(table_name3);
        bean1.setColumn_check(column_pdf);
        bean1.setColumn_check_value(value_pdf);
        list1.add(bean1);
        return list1;
    }

    public List<String> showDataPdfForString(String table_name3, int pdf_id) {
        List<String> column_pdf = getColumnList(table_name3);
        return column_pdf;
    }

    public byte[] generateRecordList(String jrxmlFilePath, List list) {
        byte[] reportInbytes = null;
        try {
            // JRDataSource jsr = new JREmptyDataSource();
//             Object p = list.get(0);
//             Map<String, Object> parameter = new HashMap<String, Object>();
//             parameter.put("check", p);

            JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(list);
            JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
            reportInbytes = JasperRunManager.runReportToPdf(compiledReport, null, jrBean);
            //  JasperPrint jsp = JasperFillManager.fillReport(compiledReport,parameter,jsr);

        } catch (Exception e) {
            System.out.println("TafficPoliceModel generatReport() JRException: " + e);
        }
        return reportInbytes;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (Exception ex) {
            System.out.println("ERROR: on closeConnection() in ClientResponderModel : " + ex);
        }
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
