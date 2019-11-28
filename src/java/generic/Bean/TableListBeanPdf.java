/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.List;

/**
 *
 * @author DELL
 */
public class TableListBeanPdf {
//     private List<ColnamePdf> data_pdf;
     private List<String> column_check;
       private List<String> column_check_value;
     private String pdf_tablename;
//     private String test;

//    public List<ColnamePdf> getData_pdf() {
//        return data_pdf;
//    }
//
//    public void setData_pdf(List<ColnamePdf> data_pdf) {
//        this.data_pdf = data_pdf;
//    }
//
//    public String getTest() {
//        return test;
//    }

//    public void setTest(String test) {
//        this.test = test;
//    }

    public List<String> getColumn_check() {
        return column_check;
    }

    public void setColumn_check(List<String> column_check) {
        this.column_check = column_check;
    }

    public String getPdf_tablename() {
        return pdf_tablename;
    }

    public void setPdf_tablename(String pdf_tablename) {
        this.pdf_tablename = pdf_tablename;
    }

    public List<String> getColumn_check_value() {
        return column_check_value;
    }

    public void setColumn_check_value(List<String> column_check_value) {
        this.column_check_value = column_check_value;
    }

}
