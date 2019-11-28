package Bean;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class GenericBean {
    
    private String colname;
    private String tablename;
    private String key;
    private String status;
    private String ttype;
    private String column_list;
    private String image_name;
    private String audio_name;

    public String getColumn_list() {
        return column_list;
    }

    public void setColumn_list(String column_list) {
        this.column_list = column_list;
    }

   
    
    

    public String getColname() {
        return colname;
    }

    public String getTablename() {
        return tablename;
    }

    public String getKey() {
        return key;
    }

    public String getStatus() {
        return status;
    }

    public String getTtype() {
        return ttype;
    }

    public void setColname(String colname) {
        this.colname = colname;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getAudio_name() {
        return audio_name;
    }



    public void setAudio_name(String audio_name) {
        this.audio_name = audio_name;
    }
    
    

    public void getColumn_list(List<String> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
